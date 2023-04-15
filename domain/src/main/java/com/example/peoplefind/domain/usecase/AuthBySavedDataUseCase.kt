package com.example.peoplefind.domain.usecase

import com.example.peoplefind.domain.model.request.FetchUserDataParam
import com.example.peoplefind.domain.repository.UserRepository

class AuthBySavedDataUseCase(private val userRepository: UserRepository) {
    operator fun invoke(): Boolean {
        val tokenIsExist = userRepository.fetchUserData(FetchUserDataParam(UserRepository.USER_TOKEN)).isNullOrEmpty()
        val loginState = userRepository.fetchUserData(FetchUserDataParam(UserRepository.USER_LOGGED_IN)).toBoolean()
        return tokenIsExist && loginState
    }
}