@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.presentation.chat.chatList

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.presentation.designsystem.images.ChatsyIcons
import com.example.presentation.designsystem.images.ChatsyIllustrations
import com.example.presentation.designsystem.theme.ChatsyTheme
import com.example.presentation.designsystem.theme.LocalSizes
import com.example.ui.helpers.toRelativeTime
import com.example.ui.launchers.rememberLogoutLauncher
import org.bizilabs.halo.HaloTheme
import org.bizilabs.halo.components.HaloScaffold
import org.bizilabs.halo.components.HaloTopBar
import org.bizilabs.halo.components.cards.HaloFilledCard
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDateTime
import kotlin.random.Random
import kotlin.random.nextInt

@Composable
fun ChatListScreen(
    onNavigateToSingleChat: (recipientId: Long, senderId: Long) -> Unit,
    onNavigateToLogin: () -> Unit,
    viewModel: ChatListViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    // todo handle events sent from the viewModel
    ChatListScreenContent(
        state = state,
        onAction = { action ->
            when (action) {
                is ChatListAction.OnClickChatItem -> onNavigateToSingleChat(action.recipientId, action.senderId)
                else -> viewModel.onAction(action)
            }
        },
    )
}

@Composable
private fun ChatListScreenContent(
    state: ChatListState,
    onAction: (ChatListAction) -> Unit,
) {
    val sizes = LocalSizes.current
    val logoutLauncher = rememberLogoutLauncher { onAction(ChatListAction.OnClickLogout) }
    HaloScaffold(
        topBar = {
            HaloTopBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            modifier = Modifier.size(sizes.xxl.dp),
                            imageVector = ChatsyIcons.Logo,
                            contentDescription = "Chatsy logo",
                            tint = HaloTheme.colorScheme.primary.strong,
                        )
                        Spacer(modifier = Modifier.width(sizes.xs.dp))
                        Text(
                            text = "Chats",
                            style =
                                HaloTheme.typography.bodyLarge.copy(
                                    fontSize = sizes.xl.sp,
                                    color = HaloTheme.colorScheme.content.strong,
                                ),
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        IconButton(onClick = { logoutLauncher.launch() }) {
                            Icon(
                                imageVector = ChatsyIcons.Logout,
                                contentDescription = "Chatsy logo",
                                tint = HaloTheme.colorScheme.content.strong,
                            )
                        }
                    }
                },
            )
        },
    ) { paddingValues ->
        if (state.chats.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Image(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    imageVector = ChatsyIllustrations.Empty,
                    contentDescription = "Empty image",
                )
                Spacer(modifier = Modifier.heightIn(sizes.lg.dp))
                Text(
                    text = "No chats found",
                    style =
                        HaloTheme.typography.bodyLarge.copy(
                            color = HaloTheme.colorScheme.content.neutral,
                            fontSize = sizes.sm.sp,
                        ),
                )
            }
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(paddingValues).padding(horizontal = sizes.lg.dp),
            verticalArrangement = Arrangement.spacedBy(sizes.xs.dp),
            contentPadding = PaddingValues(vertical = sizes.xxl.dp),
        ) {
            items(items = state.chats) { chat ->
                ChatItem(
                    modifier = Modifier.fillMaxWidth(),
                    userName = chat.firstAndLastName,
                    lastMessage = chat.lastMessage,
                    timeReceived = chat.lastSentOrReceived,
                    onClick = { onAction(ChatListAction.OnClickChatItem(chat.recipientId, chat.senderId)) },
                )
            }
        }
    }
}

@Composable
private fun ChatItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    userName: String,
    lastMessage: String,
    timeReceived: LocalDateTime,
) {
    val sizes = LocalSizes.current
    HaloFilledCard(
        modifier = modifier,
        onClick = onClick,
    ) {
        Row(
            modifier = modifier.padding(sizes.lg.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column {
                Text(
                    text = userName,
                    style =
                        HaloTheme.typography.bodyLarge.copy(
                            fontSize = 20.sp,
                            color = HaloTheme.colorScheme.content.stronger,
                            fontWeight = FontWeight.Bold,
                        ),
                )
                Spacer(modifier = Modifier.height(sizes.xxs.dp))
                Text(
                    text = lastMessage,
                    style =
                        HaloTheme.typography.bodyLarge.copy(
                            fontSize = sizes.md.sp,
                            color = HaloTheme.colorScheme.content.strong,
                        ),
                )
            }
            Text(
                text = timeReceived.toRelativeTime(),
                style =
                    HaloTheme.typography.bodyLarge.copy(
                        fontSize = sizes.md.sp,
                        color = HaloTheme.colorScheme.content.neutral,
                    ),
            )
        }
    }
}

@Preview
@Composable
private fun ChatListScreenPreview() {
    ChatsyTheme {
        ChatListScreenContent(
            state =
                ChatListState(
                    chats =
                        listOf(
                            ChatItemUi(
                                firstAndLastName = "Eric Wathome",
                                lastMessage = "What's good brother?",
                                lastSentOrReceived =
                                    LocalDateTime
                                        .now()
                                        .minusMinutes(Random.nextInt(0..10_180).toLong()),
                                recipientId = 1L,
                                senderId = 1
                            ),
                            ChatItemUi(
                                firstAndLastName = "Eric Wathome",
                                lastMessage = "What's good brother?",
                                lastSentOrReceived =
                                    LocalDateTime
                                        .now()
                                        .minusMinutes(Random.nextInt(0..10_180).toLong()),
                                recipientId = 1,
                                senderId = 2
                            ),
                            ChatItemUi(
                                firstAndLastName = "Eric Wathome",
                                lastMessage = "What's good brother?",
                                lastSentOrReceived =
                                    LocalDateTime
                                        .now()
                                        .minusMinutes(Random.nextInt(0..10_180).toLong()),
                                recipientId = 1,
                                senderId = 2
                            ),
                            ChatItemUi(
                                firstAndLastName = "Eric Wathome",
                                lastMessage = "What's good brother?",
                                lastSentOrReceived =
                                    LocalDateTime
                                        .now()
                                        .minusMinutes(Random.nextInt(0..10_180).toLong()),
                                recipientId = 1,
                                senderId = 2
                            ),
                        ),
                ),
            onAction = { },
        )
    }
}

@Preview
@Composable
private fun ChatItemPreview() {
    ChatsyTheme {
        ChatItem(
            modifier = Modifier.fillMaxWidth(),
            userName = "Eric Wathome",
            lastMessage = "You good today?",
            timeReceived = LocalDateTime.now().minusDays(1),
            onClick = {},
        )
    }
}
