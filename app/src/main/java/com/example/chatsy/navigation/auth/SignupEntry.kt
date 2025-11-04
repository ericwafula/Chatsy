package com.example.chatsy.navigation.auth

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.NavEntry
import com.example.chatsy.navigation.Route
import com.example.chatsy.navigation.helpers.navigateBack
import com.example.chatsy.navigation.helpers.navigateForward
import com.example.presentation.auth.signup.SignupScreen

fun signupScreen(
    key: Route.SignupScreen,
    backStack: SnapshotStateList<Route>,
): NavEntry<Route> =
    NavEntry(key) {
        SignupScreen(
            onNavigateBack = {
                backStack.navigateBack()
            },
            onNavigateToLogin = {
                backStack.navigateForward(Route.LoginScreen)
            },
            onNavigateToChatList = {
                backStack.navigateForward(Route.ChatListScreen)
            },
        )
    }
