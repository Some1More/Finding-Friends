package com.example.peoplefind.domain.usecase

import com.example.peoplefind.domain.model.Resource
import com.example.peoplefind.domain.model.request.AuthByPhoneParam
import com.example.peoplefind.domain.model.request.SaveLoginDataParam
import com.example.peoplefind.domain.model.response.UserItem
import com.example.peoplefind.domain.repository.UserRepository

class AuthByPhoneUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(authByPhoneParam: AuthByPhoneParam): Resource<UserItem> {
        val resource = userRepository.authByPhone(param = authByPhoneParam)
        resource.data?.let { data ->
            userRepository.saveLoginData(
                SaveLoginDataParam(
                    userId = data.id,
                    login = authByPhoneParam.phoneNumber,
                    password = authByPhoneParam.password,
                    rememberState = authByPhoneParam.remember
                )
            )
        }
        return resource
    }
}