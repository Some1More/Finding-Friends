package com.example.peoplefind.data.api

import com.example.peoplefind.domain.repository.UserRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    private lateinit var apiService: ApiService
    private val url = ""

    fun getApiService(userRepository: UserRepository): ApiService {
        if (!::apiService.isInitialized) {
            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient(userRepository))
                .build()
            apiService = retrofit.create(ApiService::class.java)
        }
        return apiService
    }

    private fun okHttpClient(userRepository: UserRepository): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(AuthInterceptor(userRepository))
            .build()
    }
}