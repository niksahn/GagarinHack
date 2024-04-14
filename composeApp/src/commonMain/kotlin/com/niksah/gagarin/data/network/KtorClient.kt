package com.niksah.gagarin.data.network

import com.niksah.gagarin.Constants.BASE_URL
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.resources.Resources
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class KtorClient : NetworkClient {
    private var httpClient: HttpClient =
        HttpClient(configureHttpClient())

    override val api = httpClient.createApiSpec()

    private fun configureHttpClient(): HttpClientConfig<*>.() -> Unit = {

        expectSuccess = true

        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    encodeDefaults = true
                    prettyPrint = true
                }
            )
        }
        install(HttpTimeout) {
            connectTimeoutMillis = 6000
            requestTimeoutMillis = 6000
            socketTimeoutMillis = 6000
        }
        defaultRequest {
            url(BASE_URL)
        }
        install(Resources)
        install(Logging) {
            logger = createHttpLogger()
            level = LogLevel.ALL
        }
    }

    private fun createHttpLogger(): Logger = object : Logger {
        override fun log(message: String) {
            print("OkHttp: $message")
        }
    }
}