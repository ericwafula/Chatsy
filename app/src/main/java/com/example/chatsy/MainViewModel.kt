package com.example.chatsy

import androidx.lifecycle.viewModelScope
import com.example.domain.sources.AuthLocalDatasource
import com.example.ui.helpers.StateViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

data class MainState(
    val authenticated: Boolean = false,
)

class MainViewModel(
    private val authLocalDatasource: AuthLocalDatasource,
) : StateViewModel<MainState>(MainState()) {
    init {
        checkLoginStatus()
    }

    private fun checkLoginStatus() {
        viewModelScope.launch {
            val authenticated = authLocalDatasource.isLoggedIn.firstOrNull() ?: false
            updateState { it.copy(authenticated = authenticated) }
        }
    }
}
