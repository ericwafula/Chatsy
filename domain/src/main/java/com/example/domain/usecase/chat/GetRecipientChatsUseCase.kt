package com.example.domain.usecase.chat

import com.example.domain.helpers.DataError
import com.example.domain.helpers.LocalResult
import com.example.domain.model.MessageData

interface GetRecipientChatsUseCase {
    suspend operator fun invoke(recipientId: Long): LocalResult<MessageData, DataError.Network>
}
