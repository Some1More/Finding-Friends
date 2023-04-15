package com.example.peoplefind.domain.model.request

data class RegisterAccountParam(
    val name: String,
    val phoneNumber: String,
    val password: String,
    val passwordConfirm: String
)