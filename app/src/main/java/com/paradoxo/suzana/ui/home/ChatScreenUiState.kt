package com.paradoxo.suzana.ui.home

import com.paradoxo.suzana.model.Message

data class ChatScreenUiState(
    val messages: List<Message>,
    val messageValue: String = "",
    val onMessageValueChange: (String) -> Unit = {}
)
