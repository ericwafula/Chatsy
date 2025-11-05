package com.example.datasource.remote.mappers

import com.example.datasource.remote.dtos.ChatMessageDto
import com.example.datasource.remote.dtos.MessageDataDto
import com.example.datasource.remote.dtos.P2pMessageDto
import com.example.domain.helpers.asInstant
import com.example.domain.helpers.asLocalDateTime
import com.example.domain.model.ChatMessage
import com.example.domain.model.MessageData
import com.example.domain.model.P2pMessage

fun MessageDataDto.toDomain(): MessageData =
    MessageData(
        messages = messages.map { it.toDomain() },
        count = count,
    )

fun ChatMessageDto.toDomain(): ChatMessage =
    ChatMessage(
        id = id,
        senderId = senderId,
        recipientId = recipientId,
        type = type,
        content = content,
        mediaUrl = mediaUrl,
        timestamp = timestamp.asInstant.asLocalDateTime,
        status = status,
    )

fun P2pMessageDto.toDomain(): P2pMessage =
    P2pMessage(
        type = type,
        senderId = senderId,
        recipientId = recipientId,
        content = content,
    )
