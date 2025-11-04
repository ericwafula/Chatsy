package com.example.data.di

import com.example.data.helpers.DefaultDispatcherProvider
import com.example.domain.helpers.DispatcherProvider
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val DataModule =
    module {
        singleOf(::DefaultDispatcherProvider).bind<DispatcherProvider>()
    }
