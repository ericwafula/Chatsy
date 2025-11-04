package com.example.ui.helpers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class StateViewModel<T>(
    val initial: T,
) : ViewModel() {
    private val mutableState = MutableStateFlow(initial)
    val state: StateFlow<T>
        get() = mutableState.asStateFlow()

    protected fun updateState(block: (T) -> T) {
        mutableState.update { block(it) }
    }
}

abstract class StatefulViewModel<S, E>(
    initial: S,
) : StateViewModel<S>(initial) {
    private val mutableEvent = MutableSharedFlow<E>()
    val event: SharedFlow<E>
        get() = mutableEvent.asSharedFlow()

    protected fun updateEvent(event: E) {
        viewModelScope.launch {
            mutableEvent.tryEmit(event)
        }
    }
}
