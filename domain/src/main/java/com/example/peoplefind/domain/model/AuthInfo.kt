package com.example.peoplefind.domain.model.response

data class AuthInfo(
    val userId: String,
    val expiresIn: Int,
    val tokenType: String,
    val accessToken: String,
    val refreshToken: String
)
