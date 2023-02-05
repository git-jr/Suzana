package com.paradoxo.suzana.ui.home

import com.paradoxo.suzana.model.Message

data class ChatScreenUiState(
    val messages: List<Message> = emptyList(),
    val messageValue: String = "",
    val onMessageValueChange: (String) -> Unit = {},
    val showWaitAlert: Boolean = false
)