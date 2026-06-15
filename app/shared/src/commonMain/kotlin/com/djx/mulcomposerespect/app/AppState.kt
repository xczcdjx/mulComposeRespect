package com.djx.mulcomposerespect.app

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.annotation.Single

@Single
class AppState {
    private val _token = MutableStateFlow<String?>(null)
    private val _isDark = MutableStateFlow<Boolean>(false)
    val token = _token.asStateFlow()
    val isDark = _isDark.asStateFlow()

    fun setToken(value: String?) {
        _token.value = value
    }

    fun toggleDark() {
        _isDark.value = !_isDark.value
    }
}