package com.example.presentation.auth.di

import com.example.domain.usecase.auth.LoginUseCase
import com.example.domain.usecase.auth.LoginUseCaseImpl
import com.example.domain.usecase.auth.SignupUseCase
import com.example.domain.usecase.auth.SignupUseCaseImpl
import com.example.presentation.auth.login.LoginViewModel
import com.example.presentation.auth.signup.SignupViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val AuthPresentationModule =
    module {
        viewModelOf(::LoginViewModel)
        viewModelOf(::SignupViewModel)
        factoryOf(::LoginUseCaseImpl).bind<LoginUseCase>()
        factoryOf(::SignupUseCaseImpl).bind<SignupUseCase>()
    }
