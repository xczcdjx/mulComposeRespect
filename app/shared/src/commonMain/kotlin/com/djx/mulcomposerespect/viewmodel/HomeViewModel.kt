package com.djx.mulcomposerespect.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.djx.mulcomposerespect.api.ApiService
import com.djx.mulcomposerespect.api.safeService
import com.djx.mulcomposerespect.app.AppState
import com.djx.mulcomposerespect.entities.TodoCls
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import kotlin.collections.emptyList


@KoinViewModel
class HomeViewModel(
    private val appState: AppState,
    private val apiService: ApiService
) : ViewModel() {
    private val _title = MutableStateFlow("TodoList")
    private var _toast = MutableSharedFlow<String>()

    private val _list = MutableStateFlow<List<TodoCls>>(emptyList())
    val title = _title.asStateFlow()
    val toast = _toast.asSharedFlow()

    val list = _list.asStateFlow()

    init {
        loadList()
    }

    fun loadList() {
        viewModelScope.launch {
            val res=safeService {
                apiService.getTodos()
            }
            res.success?.let {
                println(it.data)
            } ?: run {
                println(res.error)
            }
        }
    }
}