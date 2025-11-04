package com.example.domain.usecase.chat

import com.example.domain.helpers.DataError
import com.example.domain.helpers.LocalResult
import com.example.domain.model.UserWithChatInfo

fun interface GetChatUsersUseCase {
    suspend operator fun invoke(): LocalResult<List<UserWithChatInfo>, DataError>
}
