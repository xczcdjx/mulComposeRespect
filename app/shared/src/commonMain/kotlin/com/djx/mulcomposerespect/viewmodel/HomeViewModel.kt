package com.djx.mulcomposerespect.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.djx.mulcomposerespect.services.ApiService
import com.djx.mulcomposerespect.app.AppState
import com.djx.mulcomposerespect.utils.AppStorage
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel


@KoinViewModel
class HomeViewModel(
    private val appState: AppState,
    private val apiService: ApiService,
    private val appStorage: AppStorage
) : ViewModel() {
    private val countKey = "countKey"
    private val _title = MutableStateFlow("Storage Test")
    private val _count = MutableStateFlow(0)
    private var _toast = MutableSharedFlow<String>()
    val title = _title.asStateFlow()
    val toast = _toast.asSharedFlow()
    val count = _count.asStateFlow()

    init {
        load()
    }

    fun load() {
        viewModelScope.launch {
            val value = appStorage
                .getStringFlow(countKey)
                .first()

            _count.value = value?.toIntOrNull() ?: 0
        }
    }

    fun add(i: Int = 1) {
        _count.value += i
        Logger.i { "count ${_count.value}" }
        viewModelScope.launch {
            appStorage.putString(countKey, _count.value.toString())
        }
    }
}