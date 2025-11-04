package com.example.presentation.auth.signup

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class SignupState(
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

    val canSubmit: Boolean
        get() = isValidUsername && isValidPassword
}

sealed interface SignupAction {
    data class OnEnterUsername(
        val username: String,
    ) : SignupAction

    data class OnEnterPassword(
        val password: String,
    ) : SignupAction

    data object OnClickSubmit : SignupAction

    data object OnClickBack : SignupAction

    data object OnClickLogin : SignupAction
}

sealed interface SignupEvent {
    data object OnNavigateToLogin : SignupEvent
}

class SignupViewModel : ViewModel() {
    private val _state = MutableStateFlow(SignupState())
    val state = _state.asStateFlow()

    fun onAction(action: SignupAction) {
        when (action) {
            SignupAction.OnClickBack -> Unit
            is SignupAction.OnEnterUsername -> onEnterUsername(action.username)
            is SignupAction.OnEnterPassword -> onEnterPassword(action.password)
            SignupAction.OnClickLogin -> Unit
            SignupAction.OnClickSubmit -> onClickSubmit()
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
