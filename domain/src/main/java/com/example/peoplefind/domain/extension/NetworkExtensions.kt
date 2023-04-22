package com.example.peoplefind.domain.extension

import com.example.peoplefind.domain.model.ApiResult

fun <T : Any> ApiResult<T>.onLoading(
    executable: () -> Unit
): ApiResult<T> = apply {
    if (this is ApiResult.Loading) {
        executable()
    }
}

fun <T : Any> ApiResult<T>.onSuccess(
    executable: (T) -> Unit
): ApiResult<T> = apply {
    if (this is ApiResult.Success<T>) {
        executable(data)
    }
}

fun <T : Any> ApiResult<T>.onFailure(
    executable: (errorMessage: String, code: Int) -> Unit
): ApiResult<T> = apply {
    if (this is ApiResult.Failure) {
        executable(errorMessage, code)
    }
}