package com.example.chatsy.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.ui.NavDisplay
import com.example.chatsy.navigation.auth.loginScreen
import com.example.chatsy.navigation.auth.signupScreen
import com.example.chatsy.navigation.chat.chatListScreen

@Composable
fun RootNav(startDestination: Route) {
    val backStack = remember { mutableStateListOf(startDestination) }

    NavDisplay(
        backStack = backStack,
    ) { entry ->
        when (entry) {
            is Route.LoginScreen -> loginScreen(key = entry, backStack = backStack)
            is Route.SignupScreen -> signupScreen(key = entry, backStack = backStack)
            is Route.ChatListScreen -> chatListScreen(key = entry, backStack = backStack)
            is Route.ChatScreen -> TODO()
        }
    }
}
