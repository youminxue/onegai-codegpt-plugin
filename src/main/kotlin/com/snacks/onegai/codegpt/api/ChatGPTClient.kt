package com.snacks.onegai.codegpt.api

interface ChatGPTClient {
    fun ask(question: String): String
}