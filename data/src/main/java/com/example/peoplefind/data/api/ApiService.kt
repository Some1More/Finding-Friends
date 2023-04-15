package com.example.peoplefind.data.api

import com.example.peoplefind.domain.model.request.AuthByPhoneParam
import com.example.peoplefind.domain.model.request.RegisterAccountParam
import com.example.peoplefind.domain.model.response.UserItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/api/User/Registration")
    fun registerUser(@Body request: RegisterAccountParam): Response<UserItem>

    @POST("/api/User/Authorization")
    fun authUserByPhone(@Body request: AuthByPhoneParam): Response<UserItem>
}