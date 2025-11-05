package com.example.presentation.auth.login

import androidx.lifecycle.viewModelScope
import com.example.domain.helpers.onError
import com.example.domain.helpers.onSuccess
import com.example.domain.sources.auth.AuthRemoteDataSource
import com.example.ui.helpers.StatefulViewModel
import com.example.ui.helpers.asUiText
import kotlinx.coroutines.launch
import timber.log.Timber

data class LoginState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
    val showDialog: Boolean = false,
    val username: String = "",
    val password: String = "#Password-123",
    val navigateToChats: Boolean = false
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
    data object NavigateToChats : LoginEvent
}

class LoginViewModel(
    private val authRemoteDataSource: AuthRemoteDataSource,
) : StatefulViewModel<LoginState, LoginEvent>(LoginState()) {
    fun onAction(action: LoginAction) {
        when (action) {
            is LoginAction.OnEnterUsername -> onEnterUsername(action.username)
            is LoginAction.OnEnterPassword -> onEnterPassword(action.password)
            LoginAction.OnClickSignup -> Unit
            LoginAction.OnClickSubmit -> onClickSubmit()
        }
    }

    private fun onEnterUsername(username: String) {
        updateState { it.copy(username = username) }
    }

    private fun onEnterPassword(password: String) {
        updateState { it.copy(password = password) }
    }

    private fun onClickSubmit() {
        val email = state.value.username.plus("@test.com")
        val password = state.value.password
        viewModelScope.launch {
            updateState { it.copy(isLoading = true) }
            authRemoteDataSource
                .login(email, password)
                .onSuccess {
                    updateState { it.copy(isLoading = false, navigateToChats = true) }
                    updateEvent(LoginEvent.NavigateToChats)
                }.onError { value ->
                    updateState { it.copy(isLoading = false, errorMessage = value.asUiText()) }
                }
        }
    }
}
