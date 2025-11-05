package com.example.domain.usecase.auth

import com.example.domain.helpers.DataError
import com.example.domain.helpers.LocalResult
import com.example.domain.helpers.asEmptyDataResult
import com.example.domain.sources.auth.AuthLocalDatasource
import com.example.domain.sources.auth.AuthRemoteDataSource

interface LoginUseCase {
    suspend operator fun invoke(
        email: String,
        password: String,
    ): LocalResult<Unit, DataError.Network>
}

class LoginUseCaseImpl(
    private val authLocalDatasource: AuthLocalDatasource,
    private val authRemoteDataSource: AuthRemoteDataSource,
) : LoginUseCase {
    override suspend operator fun invoke(
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
