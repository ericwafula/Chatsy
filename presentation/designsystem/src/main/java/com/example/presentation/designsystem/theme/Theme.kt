package com.example.presentation.designsystem.theme

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalDensity
import com.example.presentation.designsystem.helpers.SetupSystemBar
import org.bizilabs.halo.HaloTheme

@Composable
fun ChatsyTheme(
    insetsPadding: ChatsyPadding =
        ChatsyPadding(
            statusBar = WindowInsets.statusBars.asPaddingValues().calculateTopPadding(),
            navigationBarAndInsets =
                maxOf(
                    WindowInsets.navigationBars.getBottom(LocalDensity.current),
                    WindowInsets.ime.getBottom(LocalDensity.current),
                ).toDp(),
        ),
    sizes: ChatsySizes =
        ChatsySizes(
            rs = 2,
            xxs = 4,
            xs = 8,
            sm = 12,
            md = 14,
            lg = 16,
            xl = 24,
            xxl = 32,
            el = 48,
        ),
    content: @Composable () -> Unit,
) {
    SetupSystemBar(
        colorScheme = DarkColors,
        isDarkModeEnabled = true,
    )

    CompositionLocalProvider(
        provideChatsyPadding(insetsPadding),
        provideChatsySizes(sizes),
    ) {
        HaloTheme(
            colorScheme = DarkColors,
            content = content,
        )
    }
}
