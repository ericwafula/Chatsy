package com.example.domain.sources.chat

import com.example.domain.helpers.DataError
import com.example.domain.helpers.LocalResult
import com.example.domain.model.UserWithChatInfo

interface ChatRemoteSource {
    suspend fun getChatUsers(): LocalResult<List<UserWithChatInfo>, DataError.Network>
}
