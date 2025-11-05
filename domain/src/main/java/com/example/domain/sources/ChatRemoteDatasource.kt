package com.example.domain.sources

import com.example.domain.helpers.DataError
import com.example.domain.helpers.LocalResult
import com.example.domain.model.MessageData
import com.example.domain.model.P2pMessage
import kotlinx.coroutines.flow.Flow

interface ChatRemoteDatasource {
    suspend fun getMessages(recipientId: Long): LocalResult<MessageData, DataError.Network>

    suspend fun listenToSocket(token: String): Flow<P2pMessage>
}
