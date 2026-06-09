package com.djx.mulcomposerespect.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.djx.mulcomposerespect.api.ApiService
import com.djx.mulcomposerespect.app.AppState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel


@KoinViewModel
class HomeViewModel(
    private val appState: AppState,
    private val apiService: ApiService
) : ViewModel() {
    private val _title = MutableStateFlow("KoinViewModel")
    private var _count = MutableStateFlow(0)
    private var _toast = MutableSharedFlow<String>()

    val title = _title.asStateFlow()
    val count = _count.asStateFlow()
    val toast = _toast.asSharedFlow()

    init {
        load()
        testRequest()
    }

    fun load() {
        _count.value = 0
    }

    fun add(i: Int) {
        _count.value += i;
        appState.setToken("count: ${_count.value}")
        viewModelScope.launch {
            _toast.emit("当前 count = ${_count.value}")
        }
        testRequest()
    }
    fun testRequest() {
        viewModelScope.launch {
            try {
                val txt = apiService.getHello()
                println("请求成功: $txt")
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
    }
}