package com.example.presentation.chat.chatList

import androidx.lifecycle.viewModelScope
import com.example.domain.helpers.DataError
import com.example.domain.helpers.LocalResult
import com.example.domain.helpers.onError
import com.example.domain.helpers.onSuccess
import com.example.domain.model.UserDomain
import com.example.domain.model.UserWithChatInfo
import com.example.domain.usecase.chat.GetChatUsersUseCase
import com.example.domain.usecase.user.GetUsersUseCase
import com.example.ui.helpers.StatefulViewModel
import kotlinx.coroutines.launch
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
    val senderId: Long? = null,
    val chatsState: LocalResult<List<UserWithChatInfo>, DataError>? = null,
    val usersState: LocalResult<List<UserDomain>, DataError>? = null,
)

sealed interface ChatListEvent {
    data object NavigateToLogin : ChatListEvent

    data class NavigateToChat(
        val recipientId: Long,
        val senderId: Long,
    ) : ChatListEvent
}

sealed interface ChatListAction {
    data object OnClickLogout : ChatListAction

    data class OnClickChatItem(
        val recipientId: Long,
        val senderId: Long,
    ) : ChatListAction

    data object OnClickAddChat : ChatListAction
}

class ChatListViewModel(
    private val getChatUsersUseCase: GetChatUsersUseCase,
    private val getUsersUseCase: GetUsersUseCase,
) : StatefulViewModel<ChatListState, ChatListEvent>(ChatListState()) {
    init {
        getUserChats()
        getUsers()
    }

    private fun getUserChats() {
        viewModelScope.launch {
            getChatUsersUseCase()
                .onSuccess { result -> updateState { it.copy(chatsState = LocalResult.Success(result)) } }
                .onError { result ->
                    updateState { it.copy(chatsState = LocalResult.Error(result)) }
                }
        }
    }

    private fun getUsers() {
        viewModelScope.launch {
            getUsersUseCase()
                .onSuccess { result -> updateState { it.copy(usersState = LocalResult.Success(result)) } }
                .onError { result ->
                    updateState { it.copy(usersState = LocalResult.Error(result)) }
                }
        }
    }

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
