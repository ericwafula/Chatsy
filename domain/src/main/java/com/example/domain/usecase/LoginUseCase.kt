package com.example.domain.usecase

import com.example.domain.helpers.DataError
import com.example.domain.helpers.LocalResult
import com.example.domain.helpers.asEmptyDataResult
import com.example.domain.model.AuthResponse
import com.example.domain.sources.AuthLocalDatasource
import com.example.domain.sources.AuthRemoteDataSource

class LoginUseCase(
    private val authLocalDatasource: AuthLocalDatasource,
    private val authRemoteDataSource: AuthRemoteDataSource,
) {
    suspend operator fun invoke(
        email: String,
        password: String,
    ): LocalResult<Unit, DataError.Network> {
        val result = authRemoteDataSource.login(email, password)

        if (result is LocalResult.Success) {
            val token = result.data.token

            authLocalDatasource.run {
                setAccessToken(token)
                setLoggedInStatus(true)
            }
        }

        return result.asEmptyDataResult()
    }
}
