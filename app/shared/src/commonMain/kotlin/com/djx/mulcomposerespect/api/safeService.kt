package com.djx.mulcomposerespect.api

import co.touchlab.kermit.Logger
import com.djx.mulcomposerespect.constants.ApiException
import kotlinx.io.IOException

enum class ResErrorType {
    request,
    network,
    io,
    exception
}

data class ApiResponse<T>(
    val success: T?,
    val error: String? = null,
    val type: ResErrorType = ResErrorType.request
)

suspend fun <T> safeService(
    apiCall: suspend () -> T
): ApiResponse<T> {
    return try {
        ApiResponse(apiCall())
    } catch (e: kotlinx.coroutines.CancellationException) {
        throw e
    } catch (e: ApiException) {
        ApiResponse(
            null,
            e.message,
            if (e.code == 408) ResErrorType.network else ResErrorType.request
        )
    } catch (e: IOException) {
        Logger.d(throwable = e, tag = "Network error") {
            e.message ?: "IOException"
        }

        ApiResponse(null, "Network error", ResErrorType.io)
    } catch (e: Exception) {
        Logger.e(throwable = e, tag = "Imperative Error") {
            e.message ?: "IOException"
        }
        ApiResponse(null, "Unknown error", ResErrorType.exception)
    }
}