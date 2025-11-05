package com.example.datasource.remote.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class P2pMessageDto(
    val type: String,
    @SerialName("sender_id")
    val senderId: Long,
    @SerialName("recipient_id")
    val recipientId: Long,
    val content: String,
)
