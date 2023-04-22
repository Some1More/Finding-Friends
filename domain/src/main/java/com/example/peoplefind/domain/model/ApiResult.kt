package com.example.peoplefind.domain.model

sealed class ApiResult<out T> {
    object Loading: ApiResult<Nothing>()

    data class Success<out T>(
        val data: T
    ): ApiResult<T>()

    data class Failure(
        val errorMessage: String,
        val code: Int,
    ): ApiResult<Nothing>()
}
