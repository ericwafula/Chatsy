package com.example.domain.usecase

import com.example.domain.helpers.DataError
import com.example.domain.helpers.LocalResult
import com.example.domain.model.MessageData
import com.example.domain.sources.ChatRemoteDatasource

class GetChatsUseCase(
    private val chatRemoteDatasource: ChatRemoteDatasource,
) {
    suspend operator fun invoke(recipientId: Long): LocalResult<MessageData, DataError.Network> =
        chatRemoteDatasource.getMessages(recipientId)
}
