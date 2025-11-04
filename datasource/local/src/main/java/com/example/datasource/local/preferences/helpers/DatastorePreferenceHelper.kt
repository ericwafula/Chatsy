package com.example.datasource.local.preferences.helpers

import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

interface DatastorePreferenceHelper {
    suspend fun <T> save(
        key: Preferences.Key<T>,
        value: T,
    )

    fun <T> get(key: Preferences.Key<T>): Flow<T?>

    suspend fun <T> update(
        key: Preferences.Key<T>,
        value: T,
    )

    suspend fun <T> delete(key: Preferences.Key<T>)
}

class DefaultDatastorePreferencesHelper(
    private val dataStore: DataStore<Preferences>,
) : DatastorePreferenceHelper {
    override suspend fun <T> save(
        key: Preferences.Key<T>,
        value: T,
    ) {
        dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    override fun <T> get(key: Preferences.Key<T>): Flow<T?> =
        dataStore.data
            .catch {
                if (it is IOException) {
                    throw it
                } else {
                    emit(emptyPreferences())
                }
            }.map { preferences ->
                preferences[key]
            }

    override suspend fun <T> update(
        key: Preferences.Key<T>,
        value: T,
    ) {
        save(key, value)
    }

    override suspend fun <T> delete(key: Preferences.Key<T>) {
        dataStore.edit { preferences ->
            preferences.remove(key)
        }
    }
}
