package com.example.ui.launchers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.designsystem.components.ChatsyBottomSheet
import com.example.presentation.designsystem.theme.LocalSizes
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.components.buttons.HaloFilledButton
import org.bizilabs.halo.components.buttons.HaloOutlineButton

class LogoutLauncher internal constructor(
    private val onLaunch: () -> Unit,
) {
    fun launch() = onLaunch()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberLogoutLauncher(onResult: () -> Unit): LogoutLauncher {
    var isVisible by remember { mutableStateOf(false) }
    val sizes = LocalSizes.current

    ChatsyBottomSheet(
        isVisible = isVisible,
        onDismissRequest = { isVisible = false },
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(sizes.lg.dp),
        ) {
            Text(
                text = "Logout",
                style =
                    HaloTheme.typography.bodyLarge.copy(
                        color = HaloTheme.colorScheme.content.strong,
                        fontWeight = FontWeight.Medium,
                        fontSize = sizes.xl.sp,
                    ),
            )
            Spacer(modifier = Modifier.height(sizes.lg.dp))
            HorizontalDivider(color = HaloTheme.colorScheme.content.weak)
            Spacer(modifier = Modifier.height(sizes.lg.dp))
            Text(
                text = "Are you sure you want to logout?",
                style =
                    HaloTheme.typography.bodyLarge.copy(
                        color = HaloTheme.colorScheme.content.neutral,
                        fontSize = sizes.sm.sp,
                    ),
            )
            Spacer(modifier = Modifier.height(sizes.xl.dp))
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(sizes.xs.dp)) {
                HaloOutlineButton(
                    modifier = Modifier.weight(1f),
                    onClick = { isVisible = false },
                ) {
                    Text(
                        text = "Cancel",
                        style =
                            HaloTheme.typography.bodyLarge.copy(
                                color = HaloTheme.colorScheme.content.neutral,
                                fontSize = sizes.sm.sp,
                            ),
                    )
                }
                HaloFilledButton(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        isVisible = false
                        onResult()
                    },
                ) {
                    Text(
                        text = "Yes",
                        style =
                            HaloTheme.typography.bodyLarge.copy(
                                color = HaloTheme.colorScheme.content.weaker,
                                fontSize = sizes.md.sp,
                            ),
                    )
                }
                Spacer(modifier = Modifier.height(sizes.lg.dp))
            }
        }
    }

    return LogoutLauncher(
        onLaunch = { isVisible = true },
    )
}
