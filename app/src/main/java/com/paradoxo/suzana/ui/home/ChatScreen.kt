package com.paradoxo.suzana.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paradoxo.suzana.R
import com.paradoxo.suzana.model.Autor
import com.paradoxo.suzana.ui.components.MessageItemAi
import com.paradoxo.suzana.ui.components.MessageItemLoad
import com.paradoxo.suzana.ui.components.MessageItemUser
import com.paradoxo.suzana.ui.theme.SuzanaTheme
import com.paradoxo.suzana.util.messageListSample

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    state: ChatScreenUiState,
    modifier: Modifier = Modifier,
    onSendMessage: () -> Unit = {},
    onOpenDialogKey: () -> Unit = {},
) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                text = stringResource(R.string.digite_sua_api_key),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary
            )
        }, actions = {
            IconButton(onClick = onOpenDialogKey) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_key),
                    "key",
                    Modifier.padding(4.dp),
                    tint = MaterialTheme.colorScheme.primary,
                )
            }
        })

    }) { paddingValues ->
        Column(modifier.padding(paddingValues)) {
            LazyColumn(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .weight(8f), reverseLayout = true
            ) {
                items(state.messages.reversed(), contentType = { it.autor }) { it ->
                    when (it.autor) {
                        Autor.AI -> {
                            MessageItemAi(value = it.text)
                        }

                        Autor.USER -> {
                            MessageItemUser(value = it.text)
                        }

                        Autor.LOAD -> {
                            MessageItemLoad()
                        }
                    }

                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            EntryTextBar(state) { onSendMessage() }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun EntryTextBar(state: ChatScreenUiState, onClickSendMessage: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .height(56.dp)
            .background(MaterialTheme.colorScheme.tertiary),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            placeholder = {
                Text(text = stringResource(R.string.manda_ai), color = Color.Gray)
            },
            value = state.messageValue,
            onValueChange = state.onMessageValueChange,
            modifier = Modifier
                .weight(5f)
                .background(color = Color.Transparent),

            // For future reference: I know... "use BasicText" instead od doing all that customization, but, just this time:
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )

        )
        IconButton(onClick = onClickSendMessage) {
            Icon(
                Icons.Filled.Send,
                "send",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .weight(1f)
            )
        }

    }
}

@Preview
@Composable
fun ChatScreenPreview() {
    SuzanaTheme {
        ChatScreen(
            ChatScreenUiState(
                messages = messageListSample,
            )
        )
    }
}