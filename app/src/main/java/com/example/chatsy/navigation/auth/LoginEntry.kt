package com.example.chatsy.navigation.auth

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.NavEntry
import com.example.chatsy.navigation.Route
import com.example.chatsy.navigation.helpers.navigateForward
import com.example.chatsy.navigation.helpers.replace
import com.example.presentation.auth.login.LoginScreen

fun loginScreen(
    key: Route.LoginScreen,
    backStack: SnapshotStateList<Route>,
): NavEntry<Route> =
    NavEntry(key) {
        LoginScreen(
            onClickSignup = {
                backStack.navigateForward(Route.SignupScreen)
            },
            navigateToChats = {
                backStack.replace(Route.ChatListScreen)
            },
        )
    }
