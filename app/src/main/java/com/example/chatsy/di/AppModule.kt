package com.example.chatsy.di

import com.example.chatsy.MainViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val AppModule =
    module {
        viewModelOf(::MainViewModel)
    }
