package com.example.peoplefind.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    private lateinit var apiService: ApiService
    private val url = ""

    fun getApiService(tokenManager: TokenManager): ApiService {
        if (!::apiService.isInitialized) {
            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient(tokenManager))
                .build()
            apiService = retrofit.create(ApiService::class.java)
        }
        return apiService
    }

    private fun okHttpClient(tokenManager: TokenManager): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(AuthInterceptor(tokenManager))
            .authenticator(AuthAuthenticator(tokenManager))
            .build()
    }
}