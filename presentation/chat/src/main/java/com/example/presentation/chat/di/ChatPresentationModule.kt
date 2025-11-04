package com.example.presentation.chat.di

import com.example.presentation.chat.chatList.ChatListViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val ChatPresentationModule =
    module {
        viewModelOf(::ChatListViewModel)
    }
