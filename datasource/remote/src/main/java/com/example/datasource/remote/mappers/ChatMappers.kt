package com.example.datasource.remote.mappers

import com.example.datasource.remote.dtos.ChatMessageDto
import com.example.datasource.remote.dtos.MessageDataDto
import com.example.domain.helpers.asInstant
import com.example.domain.helpers.asLocalDateTime
import com.example.domain.model.ChatMessage
import com.example.domain.model.MessageData

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
