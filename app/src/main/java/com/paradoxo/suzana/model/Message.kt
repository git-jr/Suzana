package com.paradoxo.suzana.model

data class Message(val text: String = "", val autor: Autor = Autor.AI)

enum class Autor() {
    LOAD, USER, AI
}