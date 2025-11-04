package com.example.chatsy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.chatsy.navigation.RootNav
import com.example.chatsy.navigation.Route
import com.example.presentation.designsystem.theme.ChatsyTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val state by viewModel.state.collectAsStateWithLifecycle()
            val startDestination =
                remember(state.isLoggedIn) {
                    if (state.isLoggedIn) {
                        Route.ChatListScreen
                    } else {
                        Route.LoginScreen
                    }
                }

            ChatsyTheme {
                RootNav(
                    startDestination = startDestination,
                )
            }
        }
    }
}
