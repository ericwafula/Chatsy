package com.example.presentation.chat.di

import com.example.domain.usecase.chat.GetChatUsersUseCase
import com.example.domain.usecase.chat.GetChatUsersUseCaseImpl
import com.example.presentation.chat.chatList.ChatListViewModel
import com.example.presentation.chat.singleChat.SingleChatViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val ChatPresentationModule =
    module {
        viewModelOf(::ChatListViewModel)
        viewModelOf(::SingleChatViewModel)
        single<GetChatUsersUseCase> { GetChatUsersUseCaseImpl(get()) }
    }
