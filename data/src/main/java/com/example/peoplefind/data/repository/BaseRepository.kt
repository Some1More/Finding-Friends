package com.example.peoplefind.data.repository

import com.example.peoplefind.data.api.ErrorResponse
import com.example.peoplefind.domain.model.ApiResult
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withTimeoutOrNull
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber
import java.io.IOException

abstract class BaseRepository {
    fun <T> apiRequestFlow(call: suspend () -> Response<T>): Flow<ApiResult<T>> = flow {
        emit(ApiResult.Loading)

        withTimeoutOrNull(20000L) {
            try {
                val response = call()

                if (response.isSuccessful) {
                    response.body()?.let { data ->
                        emit(ApiResult.Success(data))
                    }
                } else {
                    response.errorBody()?.let { error ->
                        error.close()
                        val parsedError: ErrorResponse = Gson().fromJson(error.charStream(), ErrorResponse::class.java)
                        emit(ApiResult.Failure(parsedError.failureMessage, 400))
                    }
                }
            } catch (e: HttpException) {
                emit(ApiResult.Failure(errorMessage = e.message ?: "Server doesn't respond, try again later", e.code()))
            } catch (e: IOException) {
                emit(ApiResult.Failure(errorMessage = "Please check the network connection", 400))
            } catch (e: Exception) {
                Timber.e(e, e.message)
                emit(ApiResult.Failure(errorMessage = "Something went wrong", 400))
            }
        } ?: emit(ApiResult.Failure("Timeout! Please try again later.", 408))
    }.flowOn(Dispatchers.IO)
}