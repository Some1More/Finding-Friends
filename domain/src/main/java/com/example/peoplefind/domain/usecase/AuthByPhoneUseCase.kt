package com.example.peoplefind.domain.usecase

import com.example.peoplefind.domain.model.ApiResult
import com.example.peoplefind.domain.model.request.AuthByPhoneParam
import com.example.peoplefind.domain.model.response.UserItem
import com.example.peoplefind.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class AuthByPhoneUseCase(private val userRepository: UserRepository) {
    operator fun invoke(authByPhoneParam: AuthByPhoneParam): Flow<ApiResult<UserItem>> {
        return userRepository.authByPhone(param = authByPhoneParam)
    }
}