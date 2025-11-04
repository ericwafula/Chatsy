package com.example.presentation.chat.di

import com.example.domain.usecase.GetChatsUseCase
import com.example.domain.usecase.chat.GetChatUsersUseCase
import com.example.domain.usecase.chat.GetChatUsersUseCaseImpl
import com.example.domain.usecase.user.GetUsersUseCase
import com.example.domain.usecase.user.GetUsersUseCaseImpl
import com.example.presentation.chat.chatList.ChatListViewModel
import com.example.presentation.chat.singleChat.SingleChatViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val ChatPresentationModule =
    module {
        viewModelOf(::ChatListViewModel)
        viewModelOf(::SingleChatViewModel)
        singleOf(::GetChatsUseCase)
        single<GetChatUsersUseCase> { GetChatUsersUseCaseImpl(get()) }
        single<GetUsersUseCase> { GetUsersUseCaseImpl(get()) }
    }
