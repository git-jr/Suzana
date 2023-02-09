package com.paradoxo.suzana.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paradoxo.suzana.model.Autor
import com.paradoxo.suzana.model.Message
import com.paradoxo.suzana.util.OpenAiApi
import com.paradoxo.suzana.util.messageListSample
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ChatScreenUiState())
    val uiState: StateFlow<ChatScreenUiState>
        get() = _uiState.asStateFlow()

    private lateinit var openAiApi: OpenAiApi

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

    fun setToken(token: String) {
        openAiApi = OpenAiApi(token)
    }

    fun sendMessage() {
        with(_uiState) {
            if (value.messages.last().autor == Autor.LOAD) {
                value = value.copy(
                    showWaitAlert = true
                )
                return
            }
            val messageValue = value.messageValue
            updateUi()
            searchResponse(messageValue)
        }
    }

    private fun searchResponse(messageValue: String) {
        viewModelScope.launch {
            val response: String
            try {
                response = openAiApi.getResponse(messageValue)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    showError = true,
                    error = e.toString()
                )
                _uiState.value = _uiState.value.copy(
                    messages = _uiState.value.messages.minus(Message(autor = Autor.LOAD)),
                )
                return@launch
            }

            with(_uiState) {
                value = value.copy(
                    messages = value.messages.plus(
                        Message(
                            response,
                            autor = Autor.AI
                        ),
                    ).minus(Message(autor = Autor.LOAD)),
                )
            }
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

    fun updateshowError() {
        _uiState.value = _uiState.value.copy(
            showError = false,
            error = ""
        )
    }
}