package com.example.peoplefind.domain.model.request

data class AuthByPhoneParam(
    val phoneNumber: String,
    val password: String,
    val remember: Boolean
)