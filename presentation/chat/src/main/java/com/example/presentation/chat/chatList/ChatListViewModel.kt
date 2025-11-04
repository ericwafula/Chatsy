package com.example.presentation.chat.chatList

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDateTime

data class ChatItemUi(
    val firstAndLastName: String,
    val lastMessage: String,
    val lastSentOrReceived: LocalDateTime,
    val recipientId: Long,
    val senderId: Long,
)

data class ChatListState(
    val loading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
    val chats: List<ChatItemUi> = emptyList(),
    val senderId: Long? = null
)

sealed interface ChatListAction {
    data object OnClickLogout : ChatListAction

    data class OnClickChatItem(
        val recipientId: Long,
        val senderId: Long,
    ) : ChatListAction

    data object OnClickAddChat : ChatListAction
}

sealed interface ChatListEvent {
    data object OnLogout : ChatListEvent
}

class ChatListViewModel : ViewModel() {
    private val _state = MutableStateFlow(ChatListState())
    val state = _state.asStateFlow()

    fun onAction(action: ChatListAction) {
        when (action) {
            is ChatListAction.OnClickChatItem -> Unit
            ChatListAction.OnClickLogout -> onClickLogout()
            ChatListAction.OnClickAddChat -> Unit
        }
    }

    private fun onClickLogout() {
    }
}
