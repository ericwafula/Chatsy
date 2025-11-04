package com.example.presentation.designsystem.helpers

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import org.bizilabs.halo.base.HaloColorScheme

@Composable
fun SetupSystemBar(
    colorScheme: HaloColorScheme,
    isDarkModeEnabled: Boolean,
) {
    val view = LocalView.current
    val window = (view.context as? Activity)?.window ?: return

    SideEffect {
        val backgroundColor = colorScheme.background.lowest.toArgb()
        WindowCompat.getInsetsController(window, view).apply {
            isAppearanceLightStatusBars = !isDarkModeEnabled
            isAppearanceLightNavigationBars = !isDarkModeEnabled
        }
        window.statusBarColor = backgroundColor
        window.navigationBarColor = backgroundColor
    }
}