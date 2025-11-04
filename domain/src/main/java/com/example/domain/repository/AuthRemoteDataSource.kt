package com.example.domain.repository

import com.example.domain.helpers.DataError
import com.example.domain.helpers.LocalResult
import com.example.domain.model.AuthResponse

interface AuthRemoteDataSource {
    suspend fun login(
        email: String,
        password: String,
    ): LocalResult<AuthResponse, DataError.Network>

    suspend fun signup(
        username: String,
        email: String,
        password: String,
    ): LocalResult<AuthResponse, DataError.Network>
}
