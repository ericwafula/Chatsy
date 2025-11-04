package com.example.chatsy.navigation.chat

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.NavEntry
import com.example.chatsy.navigation.Route
import com.example.chatsy.navigation.helpers.navigateForward
import com.example.chatsy.navigation.helpers.replace
import com.example.presentation.auth.login.LoginScreen
import com.example.presentation.chat.chatList.ChatListScreen

fun chatListScreen(
    key: Route.ChatListScreen,
    backStack: SnapshotStateList<Route>,
): NavEntry<Route> =
    NavEntry(key) {
        ChatListScreen(
            onNavigateToLogin = {
                backStack.replace(Route.LoginScreen)
            },
            onNavigateToSingleChat = { recipientId, senderId ->
                backStack.navigateForward(Route.ChatScreen(senderId = senderId, recipientId = recipientId))
            },
        )
    }
