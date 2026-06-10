package com.djx.mulcomposerespect.di.modules

import com.djx.mulcomposerespect.api.ApiService
import com.djx.mulcomposerespect.api.createApiService
import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.HttpClient
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import co.touchlab.kermit.Logger
import com.djx.mulcomposerespect.api.ApiException
import com.djx.mulcomposerespect.app.AppState
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.request.accept
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import kotlinx.io.IOException
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

@Module
class NetworkModule {
    @Single
    fun provideHttpClient(
        appState: AppState
    ): HttpClient {
        return HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                        explicitNulls = false
                    }
                )
            }
            install(Logging) {
                logger = object : io.ktor.client.plugins.logging.Logger {
                    override fun log(message: String) {
                        co.touchlab.kermit.Logger.d(tag = "Ktor") {
                            message
                        }
                    }
                }
                level = LogLevel.ALL
            }
            defaultRequest {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)

                header(HttpHeaders.Authorization, "")
                header(HttpHeaders.UserAgent, "KMP")
                // 携带token
                appState.token.value?.let { token ->
                    header(HttpHeaders.Authorization, "Bearer $token")
                }
            }
            HttpResponseValidator {
                validateResponse { response ->
                    val statusCode = response.status.value

                    if (statusCode !in 200..201) {
                        val errorBody = response.bodyAsText()
                        Logger.i { "token ${appState.token}" }
                        Logger.e(tag = "Network Error") {
                            "Request error: $errorBody"
                        }

                        val msg = runCatching {
                            Json.parseToJsonElement(errorBody)
                                .jsonObject["msg"]
                                ?.jsonPrimitive
                                ?.content
                        }.getOrNull()

                        throw ApiException(
                            code = statusCode,
                            message = msg ?: "NO ERROR DATA"
                        )
                    }
                }

                handleResponseExceptionWithRequest { cause, _ ->
                    if (cause is ResponseException) {
                        val statusCode = cause.response.status.value
                        val errorBody = cause.response.bodyAsText()

                        val msg = runCatching {
                            Json.parseToJsonElement(errorBody)
                                .jsonObject["msg"]
                                ?.jsonPrimitive
                                ?.content
                        }.getOrNull()

                        throw ApiException(
                            code = statusCode,
                            message = msg ?: cause.message ?: "NO ERROR DATA"
                        )
                    }

                    if (cause is IOException) {
                        Logger.e(throwable = cause, tag = "Network request fail") {
                            cause.message ?: "IOException"
                        }
                        throw cause
                    }

                    throw cause
                }
            }
        }
    }

    @Single
    fun provideKtorfit(client: HttpClient): Ktorfit {
        return Ktorfit.Builder()
            .baseUrl("http://192.168.7.238:3000/")
            .httpClient(client)
            .build()
    }

    @Single
    fun provideApiService(ktorfit: Ktorfit): ApiService {
        return ktorfit.createApiService()
    }
}