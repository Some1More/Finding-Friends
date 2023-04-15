package com.example.peoplefind.domain.repository

import com.example.peoplefind.domain.model.Resource
import com.example.peoplefind.domain.model.request.AuthByPhoneParam
import com.example.peoplefind.domain.model.request.FetchUserDataParam
import com.example.peoplefind.domain.model.request.RegisterAccountParam
import com.example.peoplefind.domain.model.request.SaveLoginDataParam
import com.example.peoplefind.domain.model.response.UserItem

interface UserRepository {
    companion object {
        const val USER_ID = "user_id"
        const val USER_TOKEN = "user_token"
        const val USER_LOGGED_IN = "user_logged_in"
    }

    suspend fun registerAccount(param: RegisterAccountParam): Resource<UserItem>

    suspend fun authByPhone(param: AuthByPhoneParam): Resource<UserItem>

    fun saveLoginData(param: SaveLoginDataParam)

    fun fetchUserData(param: FetchUserDataParam): String?

    fun removeAllUserData()
}