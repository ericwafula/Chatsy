package com.example.datasource.remote.dtos

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestDto(
    val email: String,
    val password: String,
)

@Serializable
data class SignupRequestDto(
    val username: String,
    val email: String,
    val password: String,
)

@Serializable
data class AuthResponseDto(
    val message: String,
    val token: String,
)
