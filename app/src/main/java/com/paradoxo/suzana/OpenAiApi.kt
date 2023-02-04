package com.paradoxo.suzana

import android.util.Log
import com.aallam.openai.api.completion.Choice
import com.aallam.openai.api.completion.CompletionRequest
import com.aallam.openai.api.model.Model
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI

const val TAG = "generate response"

class OpenAiApi(private val token: String) {
    private lateinit var openAi: OpenAI
    private lateinit var model: Model
    val defaultTypetModel = "text-davinci-003"

    init {
        setup(token)
    }

    private fun setup(token: String) {
        openAi = OpenAI(token = token)
    }

    suspend fun getResponse(entry: String, modelType: String = defaultTypetModel): String {
        model = openAi.model(modelId = ModelId(modelType))
        val request = CompletionRequest(
            model = model.id,
            prompt = entry,
            maxTokens = 100, // esse aqui eu vou ter que testar mais
            temperature = 0.9
        )
        val choiceList: List<Choice> = openAi.completion(request).choices
        val choice = choiceList.get(0)
        val text = choice.text

        choiceList.forEach {
            // Para a entrada: "O que é Hello World?"
            Log.i(
                TAG,
                "text ${it.text}"
            ) // resposta: "Hello World" é um programa simples que imprime ou exibe a mensagem "Hello World" ou a frase equivalente. É frequentemente utilizado como um pedaço inicial de código para testar línguas de programação, ambientes de desenvolvimento, compiladores e programas de interpretação.
            Log.i(TAG, "index ${it.index}") // resposta: 0
            Log.i(TAG, "logprobs ${it.logprobs}") // resposta: null
            Log.i(TAG, "finishReason ${it.finishReason}") // resposta: stop
        }
        return text
    }
}