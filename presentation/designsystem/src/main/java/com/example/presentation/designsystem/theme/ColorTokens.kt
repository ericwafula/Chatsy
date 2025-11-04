package com.example.presentation.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import org.bizilabs.halo.HaloDefaults
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.base.HaloColorValue

val DarkColors
    @Composable
    get() =
        HaloDefaults.ColorThemes.Sky.dark.copy(
            primary =
                HaloDefaults.ColorThemes.Sky.dark.primary.copy(
                    weaker = White,
                    weak = White,
                    neutral = White,
                    strong = Amethyst,
                    stronger = Amethyst,
                ),
            content =
                HaloDefaults.ColorThemes.Sky.dark.content.copy(
                    weaker = Black,
                    weak = White.copy(0.25f),
                    neutral = White.copy(0.5f),
                    strong = White.copy(0.75f),
                    stronger = White,
                ),
            error =
                HaloColorValue(
                    weaker = Color(0xFF5D0300),
                    weak = Color(0xFFA30500),
                    neutral = Color(0xFFFF4E47),
                    strong = Color(0xFFFFB3B0),
                    stronger = Color(0xFFFFFFFf),
                ),
            success =
                HaloColorValue(
                    weaker = Color(0xFF02270C),
                    weak = Color(0xFF013C13),
                    neutral = Color(0xFF03C03C),
                    strong = Color(0xFF9BFDB8),
                    stronger = Color(0xFFD7FEE3),
                ),
        )

@Composable
fun getColorScheme() = DarkColors
