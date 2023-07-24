package com.plcoding.translator_kmm

import io.ktor.client.HttpClient

expect class HttpClientFactory {
    fun create(): HttpClient
}