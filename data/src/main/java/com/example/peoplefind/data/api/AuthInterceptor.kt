package com.example.peoplefind.data.api

import com.example.peoplefind.domain.model.request.FetchUserDataParam
import com.example.peoplefind.domain.repository.UserRepository
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val userRepository: UserRepository) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        userRepository.fetchUserData(FetchUserDataParam(UserRepository.USER_TOKEN))?.let {
            requestBuilder.addHeader("Authorization", it)
        }
        return chain.proceed(requestBuilder.build())
    }
}