package com.example.presentation.chat.di

import com.example.domain.usecase.chat.GetChatUsersUseCase
import com.example.domain.usecase.chat.GetChatUsersUseCaseImpl
import com.example.domain.usecase.chat.GetRecipientChatsUseCase
import com.example.domain.usecase.chat.GetRecipientChatsUseCaseImpl
import com.example.domain.usecase.user.GetUsersUseCase
import com.example.domain.usecase.user.GetUsersUseCaseImpl
import com.example.presentation.chat.chatList.ChatListViewModel
import com.example.presentation.chat.singleChat.SingleChatViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val ChatPresentationModule =
    module {
        viewModelOf(::ChatListViewModel)
        viewModelOf(::SingleChatViewModel)
        singleOf(::GetRecipientChatsUseCaseImpl).bind<GetRecipientChatsUseCase>()
        single<GetChatUsersUseCase> { GetChatUsersUseCaseImpl(get()) }
        single<GetUsersUseCase> { GetUsersUseCaseImpl(get()) }
    }
