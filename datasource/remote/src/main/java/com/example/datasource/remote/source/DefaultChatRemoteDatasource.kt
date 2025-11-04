package com.example.datasource.remote.source

import com.example.datasource.remote.helpers.ApiHelper
import com.example.datasource.remote.mappers.toDomain
import com.example.domain.helpers.DataError
import com.example.domain.helpers.LocalResult
import com.example.domain.helpers.map
import com.example.domain.model.MessageData
import com.example.domain.sources.ChatRemoteDatasource

class DefaultChatRemoteDatasource(
    private val apiHelper: ApiHelper,
) : ChatRemoteDatasource {
    override suspend fun getMessages(recipientId: Long): LocalResult<MessageData, DataError.Network> =
        apiHelper.getMessagesHistory(recipientId).map { it.toDomain() }
}
