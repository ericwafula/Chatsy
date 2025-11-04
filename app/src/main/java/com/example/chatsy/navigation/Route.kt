package com.example.chatsy.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object LoginScreen : Route

    @Serializable
    data object SignupScreen : Route

    @Serializable
    data object ChatListScreen : Route

    @Serializable
    data class ChatScreen(
        val senderId: Long,
        val recipientId: Long,
    ) : Route
}
