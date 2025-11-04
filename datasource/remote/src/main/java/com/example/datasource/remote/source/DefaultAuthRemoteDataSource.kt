package com.example.datasource.remote.source

import com.example.datasource.remote.helpers.ApiHelper
import com.example.datasource.remote.mappers.toDomain
import com.example.domain.helpers.DataError
import com.example.domain.helpers.LocalResult
import com.example.domain.helpers.map
import com.example.domain.model.AuthResponse
import com.example.domain.repository.AuthRemoteDataSource

class DefaultAuthRemoteDataSource(
    private val apiHelper: ApiHelper,
) : AuthRemoteDataSource {
    override suspend fun login(
        email: String,
        password: String,
    ): LocalResult<AuthResponse, DataError.Network> = apiHelper.login(email, password).map { it.toDomain() }

    override suspend fun signup(
        username: String,
        email: String,
        password: String,
    ): LocalResult<AuthResponse, DataError.Network> = apiHelper.signup(username, email, password).map { it.toDomain() }
}
