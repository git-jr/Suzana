package com.paradoxo.suzana.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.core.graphics.toColorInt
import com.paradoxo.suzana.R
import com.paradoxo.suzana.model.Autor
import com.paradoxo.suzana.ui.components.MessageItemAi
import com.paradoxo.suzana.ui.components.MessageItemLoad
import com.paradoxo.suzana.ui.components.MessageItemUser
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
                text = "Digite sua api key",
                textAlign = TextAlign.Center,
                color = Color("#FF567AF4".toColorInt())
            )
        }, actions = {
            IconButton(onClick = onOpenDialogKey) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_key),
                    "key",
                    Modifier.padding(4.dp),
                    tint = Color("#FF567AF4".toColorInt()),
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
            .background(Color("#FFE9EFFD".toColorInt())),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            placeholder = {
                Text(text = "Manda aí!", color = Color.Gray)
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
                tint = Color("#FF567AF4".toColorInt()),
                modifier = Modifier
                    .weight(1f)
            )
        }

    }
}

@Preview
@Composable
fun ChatScreenPreview() {
    ChatScreen(
        ChatScreenUiState(
            messages = messageListSample,
        )
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertDialogAPIKey(
    modifier: Modifier = Modifier,
    onDismissClick: () -> Unit = {},
    onSavedClick: (String) -> Unit = {},
    onOpenApiSite: () -> Unit = {}
) {
    var textValue by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    Dialog(onDismissRequest = onDismissClick,
        content = {
            Column(
                modifier
                    .clip(RoundedCornerShape(5))
                    .heightIn(300.dp)
                    .background(Color.White)
                    .padding(16.dp)
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Text(
                    text = "OpenAI Key",
                    textAlign = TextAlign.Center,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Para que o app possa funcionar é necessário digitar uma chave de acesso/api key",
                    textAlign = TextAlign.Center,
                )

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Você pode gerar uma chave rapidamente através do site oficial da OpenAI, dona do ChatGPT",
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(18.dp))

                Text(
                    text = "Sua chave será usada somente durante a sessão e NÃO ficará armazenada conosco",
                    textAlign = TextAlign.Center,
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    Modifier
                        .clickable { onOpenApiSite() }
                        .background(
                            color = Color("#FF567AF4".toColorInt()),
                            RoundedCornerShape(25)
                        )
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_key),
                            "key",
                            Modifier.padding(4.dp),
                            tint = Color.White,
                        )
                    }

                    Text(
                        "Gerar chave no site oficial",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = textValue, onValueChange = { textValue = it },
                    Modifier
                        .fillMaxWidth(),
                    label = { Text("Digite api key", color = Color.Gray) }
                )
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {

                    TextButton(onClick = onDismissClick) {
                        Text("Cancelar", color = Color("#FF567AF4".toColorInt()))
                    }

                    TextButton(onClick = { onSavedClick(textValue) }) {
                        Text("Salvar", color = Color("#FF567AF4".toColorInt()))
                    }

                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        })
}


@Preview
@Composable
fun AlertDialogAPIKeyPreview() {
    AlertDialogAPIKey()
}