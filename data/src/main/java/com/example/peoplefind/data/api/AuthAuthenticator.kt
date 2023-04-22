package com.example.peoplefind.data.api

import com.example.peoplefind.domain.model.request.RefreshTokenParam
import com.example.peoplefind.domain.model.response.AuthInfo
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class AuthAuthenticator(private val tokenManager: TokenManager) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        val token = runBlocking {
            tokenManager.getToken().first()
        }
        return runBlocking {
            val newToken = getNewToken(token)

            if (!newToken.isSuccessful || newToken.body() == null)
                tokenManager.deleteToken()

            newToken.body()?.let {
                tokenManager.saveToken(it.refreshToken)
                response.request.newBuilder()
                    .header("Authorization", "Basic ${it.refreshToken}")
                    .build()
            }
        }
    }

    private fun getNewToken(refreshToken: String?): retrofit2.Response<AuthInfo> {
        val apiClient = ApiClient().getApiService(tokenManager)
        return apiClient.refreshToken(request = RefreshTokenParam("Basic $refreshToken"))
    }
}