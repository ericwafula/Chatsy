package com.example.datasource.remote.di

import com.example.datasource.remote.helpers.ApiHelper
import com.example.datasource.remote.helpers.HttpClientFactoryHelper
import com.example.datasource.remote.helpers.KtorApiHelper
import com.example.datasource.remote.source.DefaultAuthRemoteDataSource
import com.example.datasource.remote.source.chat.ChatRemoteSourceImpl
import com.example.domain.sources.AuthRemoteDataSource
import com.example.domain.sources.chat.ChatRemoteSource
import io.ktor.client.HttpClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val RemoteDatasourceModule =
    module {
        singleOf(::KtorApiHelper).bind<ApiHelper>()
        singleOf(::DefaultAuthRemoteDataSource).bind<AuthRemoteDataSource>()
        single<HttpClient> { HttpClientFactoryHelper().build() }
        single<ChatRemoteSource> { ChatRemoteSourceImpl(get()) }
    }
