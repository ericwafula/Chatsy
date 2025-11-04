package com.example.datasource.local.preferences

import com.example.domain.helpers.DataError
import com.example.domain.helpers.LocalResult
import com.example.domain.sources.AuthLocalDatasource
import kotlinx.coroutines.flow.Flow

class DefaultAuthLocalDatasource(
    private val authSource: AuthSource,
) : AuthLocalDatasource {
    override val isLoggedIn: Flow<Boolean>
        get() = authSource.isLoggedIn
    override val accessToken: Flow<String>
        get() = authSource.accessToken

    override suspend fun setLoggedInStatus(isLoggedIn: Boolean): LocalResult<Unit, DataError.Local> =
        authSource.setLoggedInStatus(isLoggedIn)

    override suspend fun setAccessToken(token: String): LocalResult<Unit, DataError.Local> = authSource.setAccessToken(token)
}
