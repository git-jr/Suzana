package com.paradoxo.suzana.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.paradoxo.suzana.model.Autor
import com.paradoxo.suzana.model.Message
import com.paradoxo.suzana.ui.components.MessageItemAi
import com.paradoxo.suzana.ui.components.MessageItemLoad
import com.paradoxo.suzana.ui.components.MessageItemUser


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(state: ChatScreenUiState, modifier: Modifier = Modifier) {

    Scaffold { paddingValues ->
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
            EntryTextBar(state)

        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun EntryTextBar(state: ChatScreenUiState) {
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

@Preview
@Composable
fun ChatScreenPreview() {
    ChatScreen(
        ChatScreenUiState(
            messages = listOf(
                Message("Olá", Autor.USER),
                Message(LoremIpsum(2).values.first(), Autor.AI),
                Message(
                    LoremIpsum(13).values.first(),
                    Autor.USER
                ),
                Message(LoremIpsum(14).values.last(), Autor.AI),
                Message(
                    LoremIpsum(10).values.first(),
                    Autor.AI
                ),
                Message(autor = Autor.LOAD)
            ),
        )
    )
}

