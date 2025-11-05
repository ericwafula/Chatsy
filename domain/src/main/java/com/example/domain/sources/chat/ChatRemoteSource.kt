package com.example.domain.sources.chat

import com.example.domain.helpers.DataError
import com.example.domain.helpers.LocalResult
import com.example.domain.model.ChatMessage
import com.example.domain.model.MessageData
import com.example.domain.model.UserWithChatInfo
import kotlinx.coroutines.flow.Flow

interface ChatRemoteSource {
    suspend fun getChatUsers(): LocalResult<List<UserWithChatInfo>, DataError.Network>

    suspend fun getMessages(recipientId: Long): LocalResult<MessageData, DataError.Network>

    suspend fun observeMessageEvents(token: String): Flow<ChatMessage>

    suspend fun sendMessage(message: String)
}
