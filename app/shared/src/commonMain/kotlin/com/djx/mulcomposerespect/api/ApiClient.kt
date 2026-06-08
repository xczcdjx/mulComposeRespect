package com.djx.mulcomposerespect.api

/*
import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.create
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object ApiClient {

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    explicitNulls = false
                }
            )
        }
    }

    private val ktorfit = Ktorfit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .httpClient(client)
        .build()

    val testApi: TestApi = ktorfit.createExampleApi()
}*/
