package com.example.peoplefind.di

import com.example.peoplefind.data.api.ApiClient
import com.example.peoplefind.data.repository.UserRepositoryImpl
import com.example.peoplefind.domain.repository.UserRepository
import org.koin.dsl.module

val dataModule = module {
    single<UserRepository> {
        UserRepositoryImpl(apiClient = ApiClient(), context = get())
    }
}