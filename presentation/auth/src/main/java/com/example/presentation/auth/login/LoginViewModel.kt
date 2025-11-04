package com.example.presentation.auth.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update

data class LoginState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
    val showDialog: Boolean = false,
    val username: String = "",
    val password: String = "",
) {
    val isValidUsername: Boolean
        get() = username.isNotEmpty() && username.length >= 3

    val isValidPassword: Boolean
        get() = password.isNotEmpty() && password.length >= 3

    val canSubmit: Boolean
        get() = isValidUsername && isValidPassword
}

sealed interface LoginAction {
    data class OnEnterUsername(
        val username: String,
    ) : LoginAction

    data class OnEnterPassword(
        val password: String,
    ) : LoginAction

    data object OnClickSignup : LoginAction

    data object OnClickSubmit : LoginAction
}

sealed interface LoginEvent {
    data object OnNavigateToChatList : LoginEvent
}

class LoginViewModel : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun onAction(action: LoginAction) {
        when (action) {
            is LoginAction.OnEnterUsername -> onEnterUsername(action.username)
            is LoginAction.OnEnterPassword -> onEnterPassword(action.password)
            LoginAction.OnClickSignup -> Unit
            LoginAction.OnClickSubmit -> onClickSubmit()
        }
    }

    private fun onEnterUsername(username: String) {
        _state.update { it.copy(username = username) }
    }

    private fun onEnterPassword(password: String) {
        _state.update { it.copy(password = password) }
    }

    private fun onClickSubmit() {
    }
}
