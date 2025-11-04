package com.example.datasource.remote.di

import com.example.datasource.remote.helpers.ApiHelper
import com.example.datasource.remote.helpers.KtorApiHelper
import com.example.datasource.remote.source.DefaultAuthRemoteDataSource
import com.example.domain.repository.AuthRemoteDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val RemoteDatasourceModule =
    module {
        singleOf(::KtorApiHelper).bind<ApiHelper>()
        singleOf(::DefaultAuthRemoteDataSource).bind<AuthRemoteDataSource>()
    }
