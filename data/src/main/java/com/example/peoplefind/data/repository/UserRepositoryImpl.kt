package com.example.peoplefind.data.repository

import android.content.Context
import android.util.Base64
import com.example.peoplefind.data.api.ApiClient
import com.example.peoplefind.domain.model.Resource
import com.example.peoplefind.domain.model.request.AuthByPhoneParam
import com.example.peoplefind.domain.model.request.FetchUserDataParam
import com.example.peoplefind.domain.model.request.RegisterAccountParam
import com.example.peoplefind.domain.model.request.SaveLoginDataParam
import com.example.peoplefind.domain.model.response.UserItem
import com.example.peoplefind.domain.repository.UserRepository
import java.nio.charset.StandardCharsets

class UserRepositoryImpl(apiClient: ApiClient, context: Context) : UserRepository, BaseRepository() {
    private val apiService = apiClient.getApiService(this)
    private val pref = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)

    override suspend fun registerAccount(param: RegisterAccountParam): Resource<UserItem> {
        return safeApiCall { apiService.registerUser(request = param) }
    }

    override suspend fun authByPhone(param: AuthByPhoneParam): Resource<UserItem> {
        return safeApiCall { apiService.authUserByPhone(request = param) }
    }

    override fun saveLoginData(param: SaveLoginDataParam) {
        saveUserData(param.userId, UserRepository.USER_ID)
        saveUserData(
            makeToken(login = param.login, password = param.password),
            UserRepository.USER_TOKEN
        )
        saveUserData(param.rememberState.toString(), UserRepository.USER_LOGGED_IN)
    }

    override fun fetchUserData(param: FetchUserDataParam): String? {
        return pref.getString(param.dataId, null)
    }

    override fun removeAllUserData() {
        pref.edit().clear().apply()
    }

    private fun saveUserData(data: String, dataId: String) {
        pref.edit().apply {
            putString(dataId, data)
            apply()
        }
    }

    private fun makeToken(login: String, password: String): String {
        val originalString = "${login}:${password}"
        val data = originalString.toByteArray(StandardCharsets.UTF_8)
        return "Basic ${Base64.encodeToString(data, Base64.NO_WRAP)}"
    }
}