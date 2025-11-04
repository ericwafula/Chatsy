package com.example.chatsy

import androidx.lifecycle.ViewModel
import com.example.domain.sources.AuthLocalDatasource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class MainState(
    val isLoggedIn: Boolean = false,
)

class MainViewModel(
    private val authLocalDatasource: AuthLocalDatasource,
) : ViewModel() {
    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    init {
        // todo handle loggedInStatus and set default nav destination
        _state.update { it.copy(isLoggedIn = false) }
    }
}
