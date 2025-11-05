package com.example.datasource.remote.helpers

import com.example.datasource.remote.BuildConfig
import com.example.datasource.remote.dtos.AuthResponseDto
import com.example.datasource.remote.dtos.LoginRequestDto
import com.example.datasource.remote.dtos.MessageDataDto
import com.example.datasource.remote.dtos.P2pMessageDto
import com.example.datasource.remote.dtos.SignupRequestDto
import com.example.datasource.remote.dtos.UserDTO
import com.example.datasource.remote.dtos.UserWithChatInfoDto
import com.example.domain.helpers.DataError
import com.example.domain.helpers.LocalResult
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow

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

    suspend fun getMessagesHistory(recipientId: Long): LocalResult<MessageDataDto, DataError.Network>

    suspend fun getUsersWithChatInfo(): LocalResult<List<UserWithChatInfoDto>, DataError.Network>

    suspend fun getUsers(): LocalResult<List<UserDTO>, DataError>

    suspend fun listenToSocket(token: String): Flow<P2pMessageDto>

    suspend fun sendMessage(message: String)
}

class KtorApiHelper(
    private val httpClient: HttpClient,
    private val webSocketClientHelper: WebSocketClientHelper,
) : ApiHelper {
    override suspend fun login(
        email: String,
        password: String,
    ): LocalResult<AuthResponseDto, DataError.Network> =
        httpClient.post<LoginRequestDto, AuthResponseDto>(
            route = ApiEndpoints.Auth.LOGIN,
            body = LoginRequestDto(email = email, password = password),
        )

    override suspend fun signup(
        username: String,
        email: String,
        password: String,
    ): LocalResult<AuthResponseDto, DataError.Network> =
        httpClient.post<SignupRequestDto, AuthResponseDto>(
            route = ApiEndpoints.Auth.SIGNUP,
            body =
                SignupRequestDto(
                    username = username,
                    email = email,
                    password = password,
                ),
        )

    override suspend fun getMessagesHistory(recipientId: Long): LocalResult<MessageDataDto, DataError.Network> =
        httpClient.get<MessageDataDto>(route = ApiEndpoints.Chat.MESSAGES_HISTORY + "/$recipientId")

    override suspend fun getUsersWithChatInfo(): LocalResult<List<UserWithChatInfoDto>, DataError.Network> =
        httpClient.get<List<UserWithChatInfoDto>>(route = ApiEndpoints.Chats.All)

    override suspend fun getUsers(): LocalResult<List<UserDTO>, DataError> = httpClient.get<List<UserDTO>>(route = ApiEndpoints.Users.All)

    override suspend fun listenToSocket(token: String): Flow<P2pMessageDto> =
        webSocketClientHelper.listenToSocket(BuildConfig.WS_CONNECTION_URL + "?token=$token")

    override suspend fun sendMessage(message: String) {
        webSocketClientHelper.sendMessage(message)
    }
}
