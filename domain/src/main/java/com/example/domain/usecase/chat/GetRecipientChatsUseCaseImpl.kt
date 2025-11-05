package com.example.domain.usecase.chat

import com.example.domain.helpers.DataError
import com.example.domain.helpers.LocalResult
import com.example.domain.model.MessageData
import com.example.domain.sources.chat.ChatRemoteSource

class GetRecipientChatsUseCaseImpl(
    private val chatRemoteDatasource: ChatRemoteSource,
) : GetRecipientChatsUseCase {
    override suspend operator fun invoke(recipientId: Long): LocalResult<MessageData, DataError.Network> =
        chatRemoteDatasource.getMessages(recipientId)
}
