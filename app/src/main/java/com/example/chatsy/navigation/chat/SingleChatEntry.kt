package com.example.chatsy.navigation.chat

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.NavEntry
import com.example.chatsy.navigation.Route
import com.example.chatsy.navigation.helpers.navigateBack
import com.example.presentation.chat.singleChat.SingleChatScreen
import io.ktor.http.parametersOf
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

fun singleChatScreen(
    key: Route.ChatScreen,
    backStack: SnapshotStateList<Route>,
): NavEntry<Route> =
    NavEntry(key) {
        SingleChatScreen(
            onNavigateBack = { backStack.navigateBack() },
            viewModel = koinViewModel(parameters = { parametersOf(key.senderId, key.recipientId) }),
        )
    }
