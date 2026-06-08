package com.djx.mulcomposerespect.di.modules

import com.djx.mulcomposerespect.api.ApiService
import com.djx.mulcomposerespect.api.createApiService
import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
class NetworkModule {
    @Single
    fun provideHttpClient(): HttpClient {
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
        }
    }

    @Single
    fun provideKtorfit(client: HttpClient): Ktorfit {
        return Ktorfit.Builder()
            .baseUrl("http://192.168.0.153:6175/")
            .httpClient(client)
            .build()
    }

    @Single
    fun provideApiService(ktorfit: Ktorfit): ApiService {
        return ktorfit.createApiService()
    }
}