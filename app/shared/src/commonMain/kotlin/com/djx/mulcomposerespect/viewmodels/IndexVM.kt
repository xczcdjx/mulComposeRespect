package com.djx.mulcomposerespect.viewmodels

import androidx.lifecycle.ViewModel
import com.djx.mulcomposerespect.app.AppState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class IndexVM(
    private val appState: AppState,
) : ViewModel() {
    private val _title = MutableStateFlow("Index Page")
    private var _toast = MutableSharedFlow<String>()
    val title = _title.asStateFlow()
    val toast = _toast.asSharedFlow()

    val isDark = appState.isDark

    init {

    }

    fun toggleDark() = appState.toggleDark()
}