package com.example.datasource.remote.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class UserWithChatInfoDto(
    val id: Long,
    val username: String,
    val email: String,
    @SerialName("last_message_content")
    val lastMessageContent: String,
    @SerialName("last_message_timestamp")
    val lastMessageTimestamp: String,
    @SerialName("last_message_sender_id")
    val lastMessageSenderId: Long,
)
