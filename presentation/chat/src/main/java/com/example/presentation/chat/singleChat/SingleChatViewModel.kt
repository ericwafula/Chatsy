package com.example.presentation.chat.singleChat

import androidx.lifecycle.ViewModel

data class SingleChatState(
    val loading: Boolean = false,
)

class SingleChatViewModel : ViewModel()
