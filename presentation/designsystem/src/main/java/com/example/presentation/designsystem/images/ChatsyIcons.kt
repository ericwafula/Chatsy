package com.example.presentation.designsystem.images

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.presentation.designsystem.R

object ChatsyIcons {
    val Logo: ImageVector
        @Composable
        get() = ImageVector.vectorResource(R.drawable.chatsyicon)

    val ArrowLeft: ImageVector
        @Composable
        get() = ImageVector.vectorResource(R.drawable.arrow_left)

    val Logout: ImageVector
        @Composable
        get() = ImageVector.vectorResource(R.drawable.log_out)

    val Send: ImageVector
        @Composable
        get() = ImageVector.vectorResource(R.drawable.send)
}
