package com.example.datasource.local.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.datasource.local.preferences.AuthSource
import com.example.datasource.local.preferences.DefaultAuthLocalDatasource
import com.example.datasource.local.preferences.DefaultAuthSource
import com.example.datasource.local.preferences.helpers.DatastorePreferenceHelper
import com.example.datasource.local.preferences.helpers.DefaultDatastorePreferencesHelper
import com.example.domain.sources.AuthLocalDatasource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

private const val PreferencesName = "local.prefs"
private val Context.datastore by preferencesDataStore(name = PreferencesName)

val LocalDatasourceModule =
    module {
        single<DataStore<Preferences>> { get<Context>().datastore }
        singleOf(::DefaultDatastorePreferencesHelper).bind<DatastorePreferenceHelper>()
        singleOf(::DefaultAuthSource).bind<AuthSource>()
        singleOf(::DefaultAuthLocalDatasource).bind<AuthLocalDatasource>()
    }
