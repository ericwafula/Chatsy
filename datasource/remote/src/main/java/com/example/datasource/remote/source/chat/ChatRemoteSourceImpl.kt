package com.example.datasource.remote.source.chat

import com.example.datasource.remote.helpers.ApiHelper
import com.example.datasource.remote.mappers.toDomain
import com.example.domain.helpers.map
import com.example.domain.sources.chat.ChatRemoteSource

internal class ChatRemoteSourceImpl(
    private val apiHelper: ApiHelper,
) : ChatRemoteSource {
    override suspend fun getChatUsers() =
        apiHelper
            .getUsersWithChatInfo()
            .map { it.map { it.toDomain() } }
}
