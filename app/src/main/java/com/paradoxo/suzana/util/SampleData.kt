package com.paradoxo.suzana.util

import com.paradoxo.suzana.model.Autor
import com.paradoxo.suzana.model.Message

val messageListSample = listOf(
    Message("Insira sua chave da API", Autor.USER),
    Message("Digite algo e envie", Autor.USER),
    Message(
        "A IA irá responder 😎",
        Autor.AI
    )
)
