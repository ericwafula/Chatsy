package com.example.domain.model

data class P2pMessage(
    val type: String,
    val senderId: Long,
    val recipientId: Long,
    val content: String,
)
