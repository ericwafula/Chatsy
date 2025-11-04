package com.example.domain.sources

import com.example.domain.helpers.DataError
import com.example.domain.helpers.LocalResult
import kotlinx.coroutines.flow.Flow

interface AuthLocalDatasource {
    val isLoggedIn: Flow<Boolean>
    val accessToken: Flow<String>

    suspend fun setLoggedInStatus(isLoggedIn: Boolean): LocalResult<Unit, DataError.Local>

    suspend fun setAccessToken(token: String): LocalResult<Unit, DataError.Local>
}
