package com.example.domain.usecase.chat

import com.example.domain.helpers.DataError
import com.example.domain.helpers.LocalResult
import com.example.domain.model.UserWithChatInfo
import com.example.domain.sources.chat.ChatRemoteSource

class GetChatUsersUseCaseImpl(
    private val remoteSource: ChatRemoteSource,
) : GetChatUsersUseCase {
    override suspend fun invoke(): LocalResult<List<UserWithChatInfo>, DataError> = remoteSource.getChatUsers()
}
