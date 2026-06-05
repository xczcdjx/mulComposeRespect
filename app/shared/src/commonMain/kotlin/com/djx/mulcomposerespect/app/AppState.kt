package com.djx.mulcomposerespect.app

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.annotation.Single

@Single
class AppState {
    private val _token = MutableStateFlow<String?>(null)
    val token = _token.asStateFlow()

    fun setToken(value: String?) {
        _token.value = value
    }
}