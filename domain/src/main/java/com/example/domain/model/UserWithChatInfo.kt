package com.example.domain.model

import java.time.LocalDateTime

data class UserWithChatInfo(
    val id: Long,
    val username: String,
    val email: String,
    val lastMessageContent: String,
    val lastMessageTimestamp: LocalDateTime,
    val lastMessageSenderId: Long,
)
