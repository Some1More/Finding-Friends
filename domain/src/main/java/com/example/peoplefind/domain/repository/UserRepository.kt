package com.example.peoplefind.domain.repository

import com.example.peoplefind.domain.model.ApiResult
import com.example.peoplefind.domain.model.request.AuthByPhoneParam
import com.example.peoplefind.domain.model.request.FetchUserDataParam
import com.example.peoplefind.domain.model.request.RegisterAccountParam
import com.example.peoplefind.domain.model.request.SaveLoginDataParam
import com.example.peoplefind.domain.model.response.UserItem
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    companion object {
        const val USER_ID = "user_id"
        const val USER_TOKEN = "user_token"
        const val USER_LOGGED_IN = "user_logged_in"
    }

    fun registerAccount(param: RegisterAccountParam): Flow<ApiResult<UserItem>>

    fun authByPhone(param: AuthByPhoneParam): Flow<ApiResult<UserItem>>

    fun saveLoginData(param: SaveLoginDataParam)

    fun fetchUserData(param: FetchUserDataParam): String?

    fun removeAllUserData()
}