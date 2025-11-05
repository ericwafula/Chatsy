package com.example.datasource.remote.source.auth

import com.example.datasource.remote.helpers.ApiHelper
import com.example.datasource.remote.helpers.AuthenticationProvider
import com.example.datasource.remote.mappers.toDomain
import com.example.domain.helpers.DataError
import com.example.domain.helpers.LocalResult
import com.example.domain.helpers.map
import com.example.domain.model.AuthResponse
import com.example.domain.sources.auth.AuthRemoteDataSource

class AuthRemoteDataSourceImpl(
    private val apiHelper: ApiHelper,
) : AuthRemoteDataSource {
    override suspend fun login(
        email: String,
        password: String,
    ): LocalResult<AuthResponse, DataError.Network> {
        // TODO 1
    }

    override suspend fun signup(
        username: String,
        email: String,
        password: String,
    ): LocalResult<AuthResponse, DataError.Network> {
        // TODO 2
    }
}
