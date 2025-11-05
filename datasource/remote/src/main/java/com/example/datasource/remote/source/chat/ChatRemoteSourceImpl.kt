package com.example.datasource.remote.source.chat

import com.example.datasource.remote.helpers.ApiHelper
import com.example.datasource.remote.mappers.toDomain
import com.example.domain.helpers.DataError
import com.example.domain.helpers.LocalResult
import com.example.domain.helpers.map
import com.example.domain.model.ChatMessage
import com.example.domain.model.MessageData
import com.example.domain.sources.chat.ChatRemoteSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class ChatRemoteSourceImpl(
    private val apiHelper: ApiHelper,
) : ChatRemoteSource {
    override suspend fun getChatUsers() =
        apiHelper
            .getUsersWithChatInfo()
            .map { it.map { it.toDomain() } }

    override suspend fun getMessages(recipientId: Long): LocalResult<MessageData, DataError.Network> =
        apiHelper.getMessagesHistory(recipientId).map { it.toDomain() }

    override suspend fun observeMessageEvents(token: String): Flow<ChatMessage> = apiHelper.listenToSocket(token).map { it.toDomain() }

    override suspend fun sendMessage(message: String) {
        apiHelper.sendMessage(message)
    }
}
