package com.example.domain.model

import java.time.LocalDateTime

data class MessageData(
    val messages: List<ChatMessage>,
    val count: Int,
)

data class ChatMessage(
    val id: Long,
    val senderId: Long,
    val recipientId: Long,
    val type: String,
    val content: String,
    val mediaUrl: String,
    val timestamp: LocalDateTime,
    val status: MessageStatus,
)

enum class MessageStatus {
    SENT,
    PENDING,
    DELIVERED,
}
