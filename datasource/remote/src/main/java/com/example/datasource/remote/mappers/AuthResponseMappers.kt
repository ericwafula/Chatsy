package com.example.datasource.remote.mappers

import com.example.datasource.remote.dtos.AuthResponseDto
import com.example.domain.model.AuthResponse

fun AuthResponseDto.toDomain(): AuthResponse =
    AuthResponse(
        message = message,
        token = token,
    )
