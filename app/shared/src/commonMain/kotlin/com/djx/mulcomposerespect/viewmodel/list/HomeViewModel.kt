package com.djx.mulcomposerespect.viewmodel.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.annotation.KoinViewModel


@KoinViewModel
class HomeViewModel(

) : ViewModel() {

    private val _title = MutableStateFlow("KoinViewModel")
    private var _count = MutableStateFlow(0)
    private var _toast = MutableSharedFlow<String>()

    val title = _title.asStateFlow()
    val count = _count.asStateFlow()
    val toast = _toast.asSharedFlow()

    init {
        load()
    }

    fun load() {
        _count.value = 0
    }

    fun add(i: Int) {
        _count.value += i;
        viewModelScope.launch {
            _toast.emit("当前 count = ${_count.value}")
        }
    }
}