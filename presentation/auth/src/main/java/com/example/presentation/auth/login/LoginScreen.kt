package com.example.presentation.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.presentation.auth.R
import com.example.presentation.auth.signup.SignupAction
import com.example.presentation.designsystem.images.ChatsyIcons
import com.example.presentation.designsystem.theme.ChatsyTheme
import com.example.presentation.designsystem.theme.LocalPadding
import com.example.presentation.designsystem.theme.LocalSizes
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.components.buttons.HaloFilledButton
import org.bizilabs.halo.components.textfields.HaloCodeFilledField
import org.bizilabs.halo.components.textfields.HaloFilledTextField
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    onClickSignup: () -> Unit,
    viewModel: LoginViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LoginScreenContent(
        state = state,
        onAction = { action ->
            when (action) {
                LoginAction.OnClickSignup -> onClickSignup()
                else -> viewModel.onAction(action)
            }
        },
    )
}

@Composable
private fun LoginScreenContent(
    state: LoginState,
    onAction: (LoginAction) -> Unit,
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
                text = "Login",
                style =
                    MaterialTheme.typography.bodySmall.copy(
                        fontSize = sizes.xl.sp,
                    ),
            )
        }
        Spacer(modifier = Modifier.height(sizes.xl.dp))
        HaloFilledTextField(
            value = state.username,
            placeholder = "Enter username...",
            onValueChange = { onAction(LoginAction.OnEnterUsername(it)) },
        )
        Spacer(modifier = Modifier.height(sizes.xl.dp))
        HaloFilledTextField(
            value = state.password,
            placeholder = "Enter password...",
            onValueChange = { onAction(LoginAction.OnEnterPassword(it)) },
        )
        Spacer(modifier = Modifier.height(sizes.xl.dp))
        Text(
            text =
                buildAnnotatedString {
                    append("Don't have an account?")
                    withLink(
                        link =
                            LinkAnnotation.Clickable(
                                tag = "signup",
                                styles =
                                    TextLinkStyles(
                                        style =
                                            SpanStyle(
                                                color = HaloTheme.colorScheme.primary.strong,
                                                fontWeight = FontWeight.Bold,
                                            ),
                                    ),
                                linkInteractionListener = { onAction(LoginAction.OnClickSignup) },
                            ),
                    ) {
                        append(" Signup")
                    }
                },
            style =
                MaterialTheme.typography.bodySmall.copy(
                    fontSize = sizes.md.sp,
                ),
        )
        Spacer(modifier = Modifier.height(sizes.xl.dp))
        Spacer(modifier = Modifier.weight(1f))
        HaloFilledButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onAction(LoginAction.OnClickSubmit) },
        ) {
            Text(
                text = "Submit",
                style =
                    MaterialTheme.typography.bodySmall.copy(
                        color = HaloTheme.colorScheme.content.weaker,
                        fontWeight = FontWeight.Bold,
                    ),
            )
        }
        Spacer(modifier = Modifier.height(sizes.xl.dp))
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    ChatsyTheme {
        LoginScreenContent(
            state = LoginState(),
            onAction = { action -> },
        )
    }
}
