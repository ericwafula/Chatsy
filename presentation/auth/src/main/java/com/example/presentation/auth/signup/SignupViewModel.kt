package com.example.presentation.auth.signup

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.helpers.onError
import com.example.domain.helpers.onSuccess
import com.example.domain.usecase.auth.SignupUseCase
import com.example.domain.usecase.auth.SignupUseCaseImpl
import com.example.ui.helpers.asUiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class SignupState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
    val showDialog: Boolean = false,
    val username: String = "",
    val email: String = "",
    val password: String = "#Password-123",
) {
    val isValidUsername: Boolean
        get() = username.isNotEmpty() && username.length >= 3

    val isValidPassword: Boolean
        get() {
            val isValidLength = password.length >= 8
            val hasSpecialCharacters = password.contains(Regex("""[./,><^!@£$%&*()\-_#:;'""]"""))
            val hasAtLeastOneUppercaseLetter = password.any { it.isUpperCase() }
            val hasAtLeastOneLowercaseLetter = password.any { it.isLowerCase() }
            val hasAtLeastOneLetter = password.any { it.isLetter() }
            val hasAtLeastOneNumber = password.any { it.isDigit() }

            return isValidLength &&
                hasSpecialCharacters &&
                hasAtLeastOneUppercaseLetter &&
                hasAtLeastOneLowercaseLetter &&
                hasAtLeastOneLetter &&
                hasAtLeastOneNumber
        }

    val constructedEmail: String
        get() = "$username@test.com"

    val canSubmit: Boolean
        get() = isValidUsername && isValidPassword
}

sealed interface SignupAction {
    data class OnEnterUsername(
        val username: String,
    ) : SignupAction

    data object OnClickSubmit : SignupAction

    data object OnClickBack : SignupAction

    data object OnClickLogin : SignupAction
}

sealed interface SignupEvent {
    data object OnNavigateToChatList : SignupEvent

    data class ShowToast(
        val message: String,
    ) : SignupEvent
}

class SignupViewModel(
    private val signupUseCase: SignupUseCase,
) : ViewModel() {
    private val _state = mutableStateOf(SignupState())
    val state: State<SignupState> = _state

    fun onAction(action: SignupAction) {
        when (action) {
            SignupAction.OnClickBack -> Unit
            SignupAction.OnClickLogin -> Unit
            SignupAction.OnClickSubmit -> onClickSubmit()
            is SignupAction.OnEnterUsername -> onEnterUsername(action.username)
        }
    }

    private fun onEnterUsername(username: String) {
        _state.value = state.value.copy(username = username)
    }

    private fun onClickSubmit() {
        viewModelScope.launch {
            _state.value = state.value.copy(isLoading = true)
            signupUseCase(
                username = state.value.username,
                email = state.value.constructedEmail,
                password = state.value.password,
            ).onSuccess {
                _state.value = state.value.copy(isLoading = false)
            }.onError { error ->
                _state.value = state.value.copy(isLoading = false)
            }
        }
    }
}
