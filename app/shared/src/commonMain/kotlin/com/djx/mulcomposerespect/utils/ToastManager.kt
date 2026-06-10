package com.djx.mulcomposerespect.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

object ToastManager {
    private val _toast = MutableSharedFlow<String>()
    val toast = _toast.asSharedFlow()

    suspend fun show(message: String) {
        _toast.emit(message)
    }

    fun show(scope: CoroutineScope, message: String) {
        scope.launch {
            _toast.emit(message)
        }
    }
}