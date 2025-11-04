package com.example.presentation.chat.singleChat

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.domain.model.ChatMessage
import com.example.domain.model.MessageStatus
import com.example.presentation.designsystem.images.ChatsyIcons
import com.example.presentation.designsystem.theme.ChatsyTheme
import com.example.presentation.designsystem.theme.LocalSizes
import com.example.ui.helpers.CollectEvent
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.components.HaloScaffold
import org.bizilabs.halo.components.HaloTopBar
import org.bizilabs.halo.components.loaders.HaloCircularProgressIndicator
import org.bizilabs.halo.components.textfields.HaloFilledTextField
import java.time.LocalDateTime

@Composable
fun SingleChatScreen(
    onNavigateBack: () -> Unit,
    viewModel: SingleChatViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    CollectEvent(viewModel.event) { event ->
        when (event) {
            is SingleChatEvent.ShowMessage -> Toast.makeText(context, event.message, Toast.LENGTH_LONG).show()
        }
    }
    SingleChatScreenContent(
        state = state,
        onAction = { action ->
            when (action) {
                SingleChatAction.OnClickBack -> onNavigateBack()
                else -> viewModel.onAction(action)
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SingleChatScreenContent(
    state: SingleChatState,
    onAction: (SingleChatAction) -> Unit,
) {
    val sizes = LocalSizes.current
    HaloScaffold(
        topBar = {
            HaloTopBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        IconButton(onClick = { onAction(SingleChatAction.OnClickBack) }) {
                            Icon(
                                modifier = Modifier.size(sizes.xxl.dp),
                                imageVector = ChatsyIcons.ArrowLeft,
                                contentDescription = "Chatsy logo",
                                tint = HaloTheme.colorScheme.content.strong,
                            )
                        }
                        Spacer(modifier = Modifier.width(sizes.xs.dp))
                        Text(
                            text = state.userName,
                            style =
                                HaloTheme.typography.bodyLarge.copy(
                                    fontSize = sizes.xl.sp,
                                    color = HaloTheme.colorScheme.content.strong,
                                ),
                        )
                    }
                },
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues = paddingValues).padding(horizontal = sizes.lg.dp),
        ) {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(sizes.lg.dp),
                contentPadding = PaddingValues(vertical = sizes.xl.dp),
            ) {
                if (state.isLoading) {
                    item {
                        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                            HaloCircularProgressIndicator(color = HaloTheme.colorScheme.primary.strong)
                        }
                    }
                }
                items(items = state.messages, key = { it.id }) { message ->
                    ChatBubble(
                        isSender = message.senderId == message.recipientId,
                        message = message.content,
                    )
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(sizes.lg.dp)) {
                HaloFilledTextField(
                    modifier = Modifier.weight(1f),
                    value = state.message,
                    onValueChange = { onAction(SingleChatAction.OnEnterMessage(it)) },
                    placeholder = "Type your message here...",
                )
                Box(
                    modifier =
                        Modifier
                            .clip(CircleShape)
                            .background(HaloTheme.colorScheme.primary.strong)
                            .size(sizes.el.dp)
                            .clickable { onAction(SingleChatAction.OnClickSend) },
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = ChatsyIcons.Send,
                        contentDescription = null,
                        tint = HaloTheme.colorScheme.content.strong,
                    )
                }
            }
            Spacer(modifier = Modifier.height(sizes.xl.dp))
        }
    }
}

@Composable
fun ChatBubble(
    isSender: Boolean,
    message: String,
) {
    val sizes = LocalSizes.current
    val senderShape = RoundedCornerShape(topStart = 100.dp, bottomStart = 100.dp, topEnd = 12.dp, bottomEnd = 40.dp)
    val receiverShape = RoundedCornerShape(topStart = 12.dp, bottomStart = 40.dp, topEnd = 100.dp, bottomEnd = 100.dp)
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement =
            if (isSender) {
                Arrangement.End
            } else {
                Arrangement.Start
            },
    ) {
        if (isSender) {
            Spacer(modifier = Modifier.width(sizes.el.dp))
            Spacer(modifier = Modifier.weight(1f))
        }
        Box(
            modifier =
                Modifier
                    .clip(
                        shape =
                            if (isSender) {
                                senderShape
                            } else {
                                receiverShape
                            },
                    ).background(
                        if (isSender) {
                            HaloTheme.colorScheme.primary.strong
                        } else {
                            HaloTheme.colorScheme.background.high
                        },
                    ).padding(horizontal = sizes.xl.dp, vertical = sizes.xxl.dp),
        ) {
            Text(text = message, style = HaloTheme.typography.bodySmall.copy(HaloTheme.colorScheme.content.strong))
        }
        if (isSender.not()) {
            Spacer(modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(sizes.el.dp))
        }
    }
}

@Preview
@Composable
private fun ChatBubblePreview() {
    ChatsyTheme {
        Box(modifier = Modifier.background(HaloTheme.colorScheme.background.lowest).padding(16.dp)) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                ChatBubble(
                    isSender = false,
                    message = "How are you doing today?",
                )
                ChatBubble(
                    isSender = true,
                    message = "Hey John!",
                )
                ChatBubble(
                    isSender = true,
                    message = "How's it going?",
                )
                ChatBubble(
                    isSender = true,
                    message =
                        """
                        Been a minute.
                        How's your fam doing and are there any updates on the previous gig?
                        """.trimIndent(),
                )
            }
        }
    }
}

@Preview
@Composable
private fun SingleChatScreenPreview() {
    ChatsyTheme {
        SingleChatScreenContent(
            state =
                SingleChatState(
                    userName = "Eric Wathome",
                    messages =
                        listOf(
                            ChatMessage(
                                id = 1,
                                senderId = 1,
                                recipientId = 2,
                                type = "",
                                content = "How are you doing today?",
                                mediaUrl = "",
                                timestamp = LocalDateTime.now(),
                                status = MessageStatus.SENT,
                            ),
                            ChatMessage(
                                id = 2,
                                senderId = 2,
                                recipientId = 2,
                                type = "",
                                content = "Hey John!",
                                mediaUrl = "",
                                timestamp = LocalDateTime.now(),
                                status = MessageStatus.SENT,
                            ),
                            ChatMessage(
                                id = 3,
                                senderId = 2,
                                recipientId = 2,
                                type = "",
                                content = "How's it going?",
                                mediaUrl = "",
                                timestamp = LocalDateTime.now(),
                                status = MessageStatus.SENT,
                            ),
                            ChatMessage(
                                id = 4,
                                senderId = 2,
                                recipientId = 2,
                                type = "",
                                content =
                                    """
                                    Been a minute.
                                    How's your fam doing and are there any updates on the previous gig?
                                    """.trimIndent(),
                                mediaUrl = "",
                                timestamp = LocalDateTime.now(),
                                status = MessageStatus.SENT,
                            ),
                        ),
                ),
            onAction = {},
        )
    }
}
