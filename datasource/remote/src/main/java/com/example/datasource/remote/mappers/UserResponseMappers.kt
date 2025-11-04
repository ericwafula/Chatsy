package com.example.datasource.remote.mappers

import com.example.datasource.remote.dtos.UserWithChatInfoDto
import com.example.domain.helpers.asInstant
import com.example.domain.helpers.asLocalDateTime
import com.example.domain.model.UserWithChatInfo
import java.time.format.DateTimeFormatter

fun UserWithChatInfoDto.toDomain(): UserWithChatInfo =
    UserWithChatInfo(
        id = id,
        username = username,
        email = email,
        lastMessageContent = lastMessageContent,
        lastMessageTimestamp = lastMessageTimestamp.asInstant.asLocalDateTime,
        lastMessageSenderId = lastMessageSenderId,
    )
