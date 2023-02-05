package com.paradoxo.suzana.ui.home

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import com.paradoxo.suzana.model.Autor
import com.paradoxo.suzana.model.Message
import com.paradoxo.suzana.util.messageListSample
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ChatViewModel : ViewModel() {


    private val _uiState = MutableStateFlow(ChatScreenUiState())
    val uiState: StateFlow<ChatScreenUiState>
        get() = _uiState.asStateFlow()


    init {
        _uiState.update { state ->
            state.copy(onMessageValueChange = {
                _uiState.value = _uiState.value.copy(
                    messageValue = it
                )
            })
        }

        _uiState.value = _uiState.value.copy(
            messages = messageListSample,
        )

    }


    fun sendMessage() {
        with(_uiState) {
            if (value.messages.last().autor == Autor.LOAD) {
                value = value.copy(
                    showWaitAlert = true
                )
                return
            }
            updateUi()
        }
    }

    private fun updateUi() {
        with(_uiState) {
            val userMessage = Message(
                text = value.messageValue, autor = Autor.USER
            )

            value = value.copy(
                messages = value.messages.plus(
                    listOf(
                        userMessage,
                        Message(autor = Autor.LOAD)
                    )
                ),
                messageValue = ""
            )
        }
    }

    fun updateWaitAlert() {

        _uiState.value = _uiState.value.copy(
            showWaitAlert = false
        )
    }
}