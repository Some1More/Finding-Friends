package com.example.peoplefind.domain.usecase

import com.example.peoplefind.domain.model.Resource
import com.example.peoplefind.domain.model.request.RegisterAccountParam
import com.example.peoplefind.domain.model.request.SaveLoginDataParam
import com.example.peoplefind.domain.model.response.UserItem
import com.example.peoplefind.domain.repository.UserRepository

class RegisterAccountUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(registerAccountParam: RegisterAccountParam): Resource<UserItem> {
        if (registerAccountParam.password != registerAccountParam.passwordConfirm) {
            return Resource.Error(errorMessage = "Пароли не совпадают")
        }
        val resource = userRepository.registerAccount(param = registerAccountParam)
        resource.data?.let { data ->
            userRepository.saveLoginData(
                SaveLoginDataParam(
                    userId = data.id,
                    login = registerAccountParam.phoneNumber,
                    password = registerAccountParam.password,
                    rememberState = true
                )
            )
        }
        return resource
    }
}