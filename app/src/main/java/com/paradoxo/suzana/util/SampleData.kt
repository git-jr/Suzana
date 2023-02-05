package com.paradoxo.suzana.util

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.paradoxo.suzana.model.Autor
import com.paradoxo.suzana.model.Message

val messageListSample = listOf(
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
)
