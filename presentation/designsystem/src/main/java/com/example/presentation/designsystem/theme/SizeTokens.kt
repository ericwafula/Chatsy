package com.example.presentation.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@ConsistentCopyVisibility
@Immutable
data class ChatsyPadding internal constructor(
    val statusBar: Dp,
    val navigationBarAndInsets: Dp,
)

@ConsistentCopyVisibility
@Immutable
data class ChatsySizes internal constructor(
    val rs: Int,
    val xxs: Int,
    val xs: Int,
    val sm: Int,
    val md: Int,
    val lg: Int,
    val xl: Int,
    val xxl: Int,
    val el: Int,
)

private val density: Density
    @Composable
    get() = LocalDensity.current

@Composable
fun Int.toDp(): Dp = with(density) { this@toDp.toDp() }

@Composable
fun Int.toSp(): TextUnit = with(density) { this@toSp.toSp() }

val LocalPadding =
    staticCompositionLocalOf {
        ChatsyPadding(
            statusBar = 0.dp,
            navigationBarAndInsets = 0.dp,
        )
    }
val LocalSizes =
    staticCompositionLocalOf {
        ChatsySizes(
            rs = 0,
            xxs = 0,
            xs = 0,
            sm = 0,
            md = 0,
            lg = 0,
            xl = 0,
            xxl = 0,
            el = 0
        )
    }

@Composable
internal fun provideChatsyPadding(padding: ChatsyPadding) = LocalPadding provides padding

@Composable
internal fun provideChatsySizes(sizes: ChatsySizes) = LocalSizes provides sizes
