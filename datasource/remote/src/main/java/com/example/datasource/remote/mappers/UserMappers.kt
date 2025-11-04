package com.example.datasource.remote.mappers

import com.example.datasource.remote.dtos.UserDTO
import com.example.domain.model.UserDomain

fun UserDTO.toDomain() =
    UserDomain(
        id = id,
        username = username,
        email = email,
        createdAt = createdAt,
    )
