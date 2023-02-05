package com.paradoxo.suzana.util

import android.util.Log
import com.aallam.openai.api.completion.Choice
import com.aallam.openai.api.completion.CompletionRequest
import com.aallam.openai.api.model.Model
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI

const val TAG = "generate response"

class OpenAiApi(token: String? = null) {
    private lateinit var openAi: OpenAI
    private lateinit var model: Model
    val defaultTypetModel = "text-davinci-003"

    init {
        token?.let { setup(it) }
    }

    private fun setup(token: String) {
        openAi = OpenAI(token = token)
    }

    suspend fun getResponse(entry: String, modelType: String = defaultTypetModel): String {
        model = openAi.model(modelId = ModelId(modelType))
        val request = CompletionRequest(
            model = model.id,
            prompt = entry,
            maxTokens = 100, // Cada palavra tema proximadamente 4 "tokens", isso limita a reposta para evitar estourar a cota da api
            temperature = 0.9
        )
        val choiceList: List<Choice> = openAi.completion(request).choices
        val choice = choiceList.get(0)
        val text = choice.text

        choiceList.forEach {
            // For entry: "O que é Hello World?"
            Log.i(
                TAG,
                "text ${it.text}"
            ) // response: "Hello World" é um programa simples que imprime ou exibe a mensagem "Hello World" ou a frase equivalente. É frequentemente utilizado como um pedaço inicial de código para testar línguas de programação, ambientes de desenvolvimento, compiladores e programas de interpretação.
            Log.i(TAG, "index ${it.index}") // response: 0
            Log.i(TAG, "logprobs ${it.logprobs}") // response: null
            Log.i(TAG, "finishReason ${it.finishReason}") // response: stop
        }
        return text.trim()
    }
}