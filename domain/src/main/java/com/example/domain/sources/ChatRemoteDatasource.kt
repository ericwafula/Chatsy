package com.example.domain.sources

import com.example.domain.helpers.DataError
import com.example.domain.helpers.LocalResult
import com.example.domain.model.MessageData

interface ChatRemoteDatasource {
    suspend fun getMessages(recipientId: Long): LocalResult<MessageData, DataError.Network>
}
