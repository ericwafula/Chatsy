package com.example.presentation.auth.signup

import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.presentation.auth.R
import com.example.presentation.designsystem.images.ChatsyIcons
import com.example.presentation.designsystem.theme.ChatsyTheme
import com.example.presentation.designsystem.theme.LocalPadding
import com.example.presentation.designsystem.theme.LocalSizes
import com.example.ui.helpers.CollectEvent
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.components.buttons.HaloFilledButton
import org.bizilabs.halo.components.loaders.HaloCircularProgressIndicator
import org.bizilabs.halo.components.textfields.HaloFilledTextField
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignupScreen(
    onNavigateBack: () -> Unit,
    onNavigateToLogin: () -> Unit,
    onNavigateToChatList: () -> Unit,
    viewModel: SignupViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    CollectEvent(viewModel.event) { event ->
        when (event) {
            SignupEvent.OnNavigateToChatList -> onNavigateToChatList()
            is SignupEvent.ShowToast ->
                Toast
                    .makeText(context, event.message, Toast.LENGTH_LONG)
                    .show()
        }
    }

    SignupScreenContent(
        state = state,
        onAction = { action ->
            when (action) {
                SignupAction.OnClickBack -> onNavigateBack()
                SignupAction.OnClickLogin -> onNavigateToLogin()
                else -> viewModel.onAction(action)
            }
        },
    )
}

@Composable
private fun SignupScreenContent(
    state: SignupState,
    onAction: (SignupAction) -> Unit,
) {
    val localPadding = LocalPadding.current
    val sizes = LocalSizes.current
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .background(HaloTheme.colorScheme.background.lowest)
                .padding(
                    top = localPadding.statusBar,
                    bottom = localPadding.navigationBarAndInsets,
                    start = sizes.lg.dp,
                    end = sizes.lg.dp,
                ).verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            modifier = Modifier.size(sizes.el.dp),
            imageVector = ChatsyIcons.Logo,
            contentDescription = stringResource(R.string.chatsy_logo),
            tint = HaloTheme.colorScheme.primary.strong,
        )
        Spacer(modifier = Modifier.height(sizes.sm.dp))
        Text(
            text = "Chatsy",
            style =
                MaterialTheme.typography.bodySmall.copy(
                    fontSize = sizes.xl.sp,
                    fontWeight = FontWeight.Bold,
                ),
        )
        Spacer(modifier = Modifier.height(sizes.xl.dp))
        HorizontalDivider(color = HaloTheme.colorScheme.content.weak)
        Spacer(modifier = Modifier.height(sizes.xl.dp))
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Signup",
                style =
                    MaterialTheme.typography.bodySmall.copy(
                        fontSize = sizes.xl.sp,
                    ),
            )
        }
        Spacer(modifier = Modifier.height(sizes.xl.dp))
        HaloFilledTextField(
            value = state.username,
            placeholder = "Username",
            onValueChange = { onAction(SignupAction.OnEnterUsername(it)) },
        )
        Spacer(modifier = Modifier.height(sizes.xl.dp))
        Spacer(modifier = Modifier.height(sizes.xl.dp))
        Spacer(modifier = Modifier.weight(1f))
        HaloFilledButton(
            modifier = Modifier.fillMaxWidth(),
            enabled = state.isLoading.not(),
            onClick = { onAction(SignupAction.OnClickSubmit) },
        ) {
            AnimatedContent(targetState = state.isLoading) { loading ->
                when(loading){
                    true -> {
                        HaloCircularProgressIndicator(
                            modifier = Modifier.size(sizes.lg.dp),
                            color = HaloTheme.colorScheme.content.strong,
                        )
                    }
                    false -> {
                        Text(
                            text = "Submit",
                            style =
                                MaterialTheme.typography.bodySmall.copy(
                                    color = HaloTheme.colorScheme.content.weaker,
                                    fontWeight = FontWeight.Bold,
                                ),
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(sizes.xl.dp))
    }
}

@Preview
@Composable
private fun SignupScreenPreview() {
    ChatsyTheme {
        SignupScreenContent(
            state =
                SignupState(
                    isLoading = true,
                ),
            onAction = { action -> },
        )
    }
}
