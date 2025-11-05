package com.example.datasource.remote.helpers

import com.example.datasource.remote.dtos.P2pMessageDto
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.webSocketSession
import io.ktor.websocket.Frame
import io.ktor.websocket.WebSocketSession
import io.ktor.websocket.close
import io.ktor.websocket.readText
import io.ktor.websocket.send
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class WebSocketClientHelper(
    private val httpClient: HttpClient,
) {
    private var session: WebSocketSession? = null
    private val json =
        Json {
            isLenient = true
            ignoreUnknownKeys = true
        }

    suspend fun sendMessage(text: String) {
        session?.send(text)
    }

    fun listenToSocket(url: String): Flow<P2pMessageDto> =
        callbackFlow {
            session =
                httpClient.webSocketSession(
                    urlString = url,
                )

            session?.let { session ->
                session
                    .incoming
                    .consumeAsFlow()
                    .filterIsInstance<Frame.Text>()
                    .collect {
                        val p2pMessageDto = json.decodeFromString<P2pMessageDto>(it.readText())
                        send(p2pMessageDto)
                    }
            } ?: run {
                session?.close()
                session = null
                close()
            }

            awaitClose {
                launch(NonCancellable) {
                    session?.close()
                    session = null
                }
            }
        }
}
