package com.example.datasource.local.preferences

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.datasource.local.preferences.helpers.DatastorePreferenceHelper
import com.example.datasource.local.preferences.helpers.safeTransaction
import com.example.domain.helpers.DataError
import com.example.domain.helpers.LocalResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface AuthSource {
    val isLoggedIn: Flow<Boolean>
    val accessToken: Flow<String>

    suspend fun setLoggedInStatus(isLoggedIn: Boolean): LocalResult<Unit, DataError.Local>

    suspend fun setAccessToken(token: String): LocalResult<Unit, DataError.Local>
}

class DefaultAuthSource(
    private val preferenceHelper: DatastorePreferenceHelper,
) : AuthSource {
    override val isLoggedIn: Flow<Boolean>
        get() = preferenceHelper.get(LoggedInStatus).map { status -> status ?: false }
    override val accessToken: Flow<String>
        get() = preferenceHelper.get(AccessToken).map { token -> token ?: "" }

    override suspend fun setLoggedInStatus(isLoggedIn: Boolean): LocalResult<Unit, DataError.Local> =
        safeTransaction {
            preferenceHelper.save(LoggedInStatus, isLoggedIn)
        }

    override suspend fun setAccessToken(token: String): LocalResult<Unit, DataError.Local> =
        safeTransaction {
            preferenceHelper.save(AccessToken, token)
        }

    companion object Keys {
        val LoggedInStatus = booleanPreferencesKey("logged_in_status")
        val AccessToken = stringPreferencesKey("access_token")
    }
}
