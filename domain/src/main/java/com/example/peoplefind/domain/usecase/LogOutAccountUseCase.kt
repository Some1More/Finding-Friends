package com.example.peoplefind.domain.usecase

import com.example.peoplefind.domain.repository.UserRepository

class LogOutAccountUseCase(private val userRepository: UserRepository) {
    operator fun invoke() {
        userRepository.removeAllUserData()
    }
}