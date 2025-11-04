package com.example.datasource.remote.helpers

object ApiEndpoints {
    object Auth {
        const val LOGIN = "users/login"
        const val SIGNUP = "users/register"
    }

    object Chat {
        const val USERS_WITH_CHAT_INFO = "users/with-chat-info"
        const val MESSAGES_HISTORY = "messages/history"
    }
}
