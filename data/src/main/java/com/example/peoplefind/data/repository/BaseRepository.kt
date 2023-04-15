package com.example.peoplefind.data.repository

import com.example.peoplefind.domain.model.Resource
import com.example.peoplefind.domain.model.response.ErrorItem
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class BaseRepository {
    suspend fun <T> safeApiCall(apiToBeCalled: suspend () -> Response<T>): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiToBeCalled()

                if (response.isSuccessful) {
                    Resource.Success(data = response.body()!!)
                } else {
                    val errorResponse = convertErrorBody(response.errorBody())
                    Resource.Error(errorMessage = errorResponse?.failureMessage ?: "Что-то пошло не так")
                }
            } catch (e: HttpException) {
                Resource.Error(errorMessage = e.message ?: "Что-то пошло не так")
            } catch (e: IOException) {
                Resource.Error("Пожалуйста, проверьте подключение к сети")
            } catch (e: Exception) {
                Resource.Error(errorMessage = "Что-то пошло не так")
            }
        }
    }

    private fun convertErrorBody(errorBody: ResponseBody?): ErrorItem? {
        return try {
            errorBody?.source()?.let {
                val moshiAdapter = Moshi.Builder().build().adapter(ErrorItem::class.java)
                moshiAdapter.fromJson(it)
            }
        } catch (e: Exception) {
            null
        }
    }
}