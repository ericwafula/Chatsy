package com.example.datasource.remote.dtos

import com.example.domain.model.MessageStatus
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class MessageDataDto(
    val messages: List<ChatMessageDto>,
    val count: Int,
)

@Serializable
data class ChatMessageDto(
    val id: Long,
    @SerialName("sender_id")
    val senderId: Long,
    @SerialName("recipient_id")
    val recipientId: Long,
    val type: String,
    val content: String,
    @SerialName("media_url")
    val mediaUrl: String,
    val timestamp: String,
    val status: MessageStatus,
)
