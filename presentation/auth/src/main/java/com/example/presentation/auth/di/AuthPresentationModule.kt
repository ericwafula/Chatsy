package com.example.presentation.auth.di

import com.example.domain.usecase.LoginUseCase
import com.example.domain.usecase.SignupUseCase
import com.example.presentation.auth.login.LoginViewModel
import com.example.presentation.auth.signup.SignupViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val AuthPresentationModule =
    module {
        viewModelOf(::LoginViewModel)
        viewModelOf(::SignupViewModel)
        factoryOf(::LoginUseCase)
        factoryOf(::SignupUseCase)
    }
