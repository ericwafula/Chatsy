package com.example.presentation.chat.singleChat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.helpers.onError
import com.example.domain.helpers.onSuccess
import com.example.domain.model.ChatMessage
import com.example.domain.sources.auth.AuthLocalDatasource
import com.example.domain.sources.chat.ChatRemoteSource
import com.example.ui.helpers.asUiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class SingleChatState(
    val isLoading: Boolean = false,
    val messages: List<ChatMessage> = emptyList(),
    val message: String = "",
    val userName: String = "",
    val senderId: Long? = null,
)

sealed interface SingleChatAction {
    data object OnClickBack : SingleChatAction

    data class OnEnterMessage(
        val message: String,
    ) : SingleChatAction

    data object OnClickSend : SingleChatAction
}

sealed interface SingleChatEvent {
    data class ShowMessage(
        val message: String,
    ) : SingleChatEvent
}

class SingleChatViewModel(
    private val senderId: Long,
    private val recipientId: Long,
    private val chatRemoteSource: ChatRemoteSource,
    private val authLocalDatasource: AuthLocalDatasource,
) : ViewModel() {
    private val _event = Channel<SingleChatEvent>()
    val event = _event.receiveAsFlow()

    private val _state = MutableStateFlow(SingleChatState())
    val state =
        _state
            .onStart {
                initState()
                getChats()
                observeChats()
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = _state.value,
            )

    fun onAction(action: SingleChatAction) {
        when (action) {
            SingleChatAction.OnClickBack -> Unit
            is SingleChatAction.OnEnterMessage -> onEnterMessage(action.message)
            SingleChatAction.OnClickSend -> onClickSend()
        }
    }

    private fun onClickSend() {
        viewModelScope.launch {
            chatRemoteSource.sendMessage(state.value.message)
        }
    }

    private fun onEnterMessage(message: String) {
        _state.update {
            it.copy(message = message)
        }
    }

    private fun initState() {
        _state.update { it.copy(senderId = senderId) }
    }

    private fun getChats() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            chatRemoteSource
                .getMessages(recipientId)
                .onSuccess { result ->
                    _state.update { it.copy(isLoading = false, messages = result.messages) }
                }.onError { error ->
                    _state.update { it.copy(isLoading = false) }
                    _event.send(SingleChatEvent.ShowMessage(error.asUiText()))
                }
        }
    }

    private fun observeChats() {
        viewModelScope.launch {
            val accessToken = authLocalDatasource.accessToken.firstOrNull() ?: ""
            chatRemoteSource.observeMessageEvents(accessToken).collectLatest { message ->
                _state.update {
                    it.copy(
                        messages = state.value.messages + message,
                    )
                }
            }
        }
    }
}
