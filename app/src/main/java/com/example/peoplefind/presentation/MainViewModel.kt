package com.example.peoplefind.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.peoplefind.domain.model.ApiResult
import com.example.peoplefind.domain.model.request.RegisterAccountParam
import com.example.peoplefind.domain.model.response.UserItem
import com.example.peoplefind.domain.usecase.RegisterAccountUseCase

class MainViewModel(private val registerAccountUseCase: RegisterAccountUseCase) : BaseViewModel() {
    private val userMutable = MutableLiveData<ApiResult<UserItem>>()
    val user: LiveData<ApiResult<UserItem>> = userMutable

    fun getUserData(coroutinesErrorHandler: CoroutinesErrorHandler) = baseRequest(
        userMutable, coroutinesErrorHandler
    ) {
        registerAccountUseCase(RegisterAccountParam("", "", "", ""))
    }
}