package com.djx.mulcomposerespect.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.djx.mulcomposerespect.entities.TodoBody
import com.djx.mulcomposerespect.utils.AppStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class CountVM(
    private val appStorage: AppStorage
) : ViewModel() {
    private val countKey = "countKey"
    private val _count = MutableStateFlow(0)
    val count = _count.asStateFlow()

    init {
        load()
    }

    fun load() {
        viewModelScope.launch {
            // 临时取值
            val countValue = appStorage.getStringFlow(countKey)
//            _count.value = countValue.first()?.toIntOrNull() ?: 0
            countValue.collect {
                _count.value = it?.toIntOrNull() ?: 0
            }
        }
    }

    fun add(i: Int = 1) {
        _count.value += i
        Logger.Companion.i { "count ${_count.value}" }
        viewModelScope.launch {
            appStorage.putString(countKey, _count.value.toString())
            appStorage.putObject("todo", TodoBody(_count.value.toString()))
            appStorage.putList(
                "todoList", listOf(
                    TodoBody(_count.value.toString(), "bbbbb"),
                    TodoBody(_count.value.toString(), "aaaaa")
                )
            )
        }
    }
}