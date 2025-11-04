package com.example.presentation.chat.singleChat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.ChatMessage
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

data class SingleChatState(
    val loading: Boolean = false,
    val messages: List<ChatMessage> = emptyList(),
    val message: String = "",
    val userName: String = ""
)

sealed interface SingleChatAction {
    data object OnClickBack : SingleChatAction

    data class OnEnterMessage(
        val message: String,
    ) : SingleChatAction

    data object OnClickSend : SingleChatAction
}

sealed interface SingleChatEvent

class SingleChatViewModel(
    private val senderId: Long,
    private val recipientId: Long,
) : ViewModel() {
    private val _event = Channel<SingleChatEvent>()
    val event = _event.receiveAsFlow()

    private val _state = MutableStateFlow(SingleChatState())
    val state =
        _state
            .onStart {
                // todo fetch chat list data
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
        // todo handle sending message
    }

    private fun onEnterMessage(message: String) {
        _state.update {
            it.copy(message = message)
        }
    }
}
