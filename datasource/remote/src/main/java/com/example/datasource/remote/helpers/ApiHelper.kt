package com.example.datasource.remote.helpers

import com.example.datasource.remote.dtos.AuthResponseDto
import com.example.datasource.remote.dtos.LoginRequestDto
import com.example.datasource.remote.dtos.MessageDataDto
import com.example.datasource.remote.dtos.SignupRequestDto
import com.example.datasource.remote.dtos.UserWithChatInfoDto
import com.example.domain.helpers.DataError
import com.example.domain.helpers.LocalResult
import com.example.domain.model.MessageData
import io.ktor.client.HttpClient
import io.ktor.client.request.get

interface ApiHelper {
    suspend fun login(
        email: String,
        password: String,
    ): LocalResult<AuthResponseDto, DataError.Network>

    suspend fun signup(
        username: String,
        email: String,
        password: String,
    ): LocalResult<AuthResponseDto, DataError.Network>

    suspend fun getUsersWithChatInfo(): LocalResult<UserWithChatInfoDto, DataError.Network>

    suspend fun getMessagesHistory(recipientId: Long): LocalResult<MessageDataDto, DataError.Network>
}

class KtorApiHelper(
    private val client: HttpClient,
) : ApiHelper {
    override suspend fun login(
        email: String,
        password: String,
    ): LocalResult<AuthResponseDto, DataError.Network> =
        client.post<LoginRequestDto, AuthResponseDto>(
            route = ApiEndpoints.Auth.LOGIN,
            body = LoginRequestDto(email = email, password = password),
        )

    override suspend fun signup(
        username: String,
        email: String,
        password: String,
    ): LocalResult<AuthResponseDto, DataError.Network> =
        client.post<SignupRequestDto, AuthResponseDto>(
            route = ApiEndpoints.Auth.SIGNUP,
            body =
                SignupRequestDto(
                    username = username,
                    email = email,
                    password = password,
                ),
        )

    override suspend fun getUsersWithChatInfo(): LocalResult<UserWithChatInfoDto, DataError.Network> =
        client.get<UserWithChatInfoDto>(route = ApiEndpoints.Chat.USERS_WITH_CHAT_INFO)

    override suspend fun getMessagesHistory(recipientId: Long): LocalResult<MessageDataDto, DataError.Network> =
        client.get<MessageDataDto>(route = ApiEndpoints.Chat.MESSAGES_HISTORY + "/$recipientId")
}
