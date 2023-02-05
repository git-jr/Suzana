package com.paradoxo.suzana.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.paradoxo.suzana.model.Message


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(state: ChatScreenUiState, modifier: Modifier = Modifier) {

    Scaffold { paddingValues ->
        Column(modifier.padding(paddingValues)) {
            LazyColumn(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .weight(8f),
                reverseLayout = true
            ) {
                items(state.messages.reversed()) {
                    if (it.autorIsAi) {
                        // MessageItemAi(value = it.text)
                        MessageItemUser(value = it.text)
                    } else {
                        // MessageItemUser(value = it.text)
                        MessageItemAi(value = it.text)
                    }
                }
            }

            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier
                    .height(56.dp)
                    .background(Color("#FFE9EFFD".toColorInt())),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    placeholder = {
                        Text(text = "Manda aí!", color = Color.DarkGray)
                    },
                    value = state.messageValue,
                    onValueChange = state.onMessageValueChange,
                    modifier = Modifier
                        .weight(5f)
                        .background(color = Color.Transparent),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )

                )
                Icon(
                    Icons.Filled.Send,
                    "menu",
                    tint = Color("#FF567AF4".toColorInt()),
                    modifier = Modifier.weight(1f)
                )
            }

        }
    }
}


@Composable
fun MessageItemAi(value: String) {
    Column(Modifier.padding(vertical = 8.dp)) {
        Row {
            Box(modifier = Modifier.weight(5f)) {
                Text(
                    value,
                    Modifier
                        .background(
                            color = Color("#FFE9EFFD".toColorInt()),
                            shape = RoundedCornerShape(0, 25, 25, 25)
                        )
                        .padding(16.dp),
                    color = Color.Black,
                )
            }
            Spacer(Modifier.size(50.dp))
        }
    }
}

@Composable
fun MessageItemUser(value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.End
    ) {
        Row {
            Spacer(Modifier.width(50.dp))
            Text(
                value,
                Modifier
                    .background(
                        color = Color("#FF567AF4".toColorInt()),
                        shape = RoundedCornerShape(25, 25, 0, 25)
                    )
                    .padding(16.dp),
                color = Color.White
            )
        }
    }
}


@Preview
@Composable
fun ChatScreenPreview() {
    ChatScreen(
        ChatScreenUiState(
            messages = listOf(
                Message("Olá", false),
                Message("Teste, olá", true),
                Message(
                    "Testeasdddddddddddddddddddddaaaaaaaaaaaaaassssssssssssdddddddddddddddwwwwwww, 3",
                    false
                ),
                Message("Mais eummmmmmmmmmmmmmmmmmmmmmmmmmmmadsaaaaaaaaaaaaaas", true),
                Message(
                    "jkasdjadjasssssssssssssssd asdddddddddddd asdkajsdahdasdasdhasda asda asd",
                    true
                ),
            ),
        )
    )
}

