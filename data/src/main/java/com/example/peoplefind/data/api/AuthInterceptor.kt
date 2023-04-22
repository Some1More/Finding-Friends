package com.example.peoplefind.data.api

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val tokenManager: TokenManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking {
            tokenManager.getToken().first()
        }
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader("Authorization", "Basic $token")

        return chain.proceed(requestBuilder.build())
    }
}