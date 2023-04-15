package com.example.peoplefind.domain.model.request

data class SaveLoginDataParam(
    val userId: String,
    val login: String,
    val password: String,
    val rememberState: Boolean
)