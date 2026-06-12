package com.djx.mulcomposerespect.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.djx.mulcomposerespect.services.ApiService
import com.djx.mulcomposerespect.api.safeService
import com.djx.mulcomposerespect.app.AppState
import com.djx.mulcomposerespect.entities.TodoBody
import com.djx.mulcomposerespect.entities.TodoCls
import kotlinx.coroutines.delay
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

    var loading by mutableStateOf(true)
        private set
    var showDialog by mutableStateOf(false)
        private set

    var todoItem by mutableStateOf(TodoBody())
        private set

    init {
        loadList(true)
    }

    fun loadList(isLoading: Boolean=false) {
        loading=isLoading
        viewModelScope.launch {
            if (isLoading) delay(2000)
            val res = safeService {
                apiService.getTodos()
            }
            res.success?.let {
                _list.value = it.data
                loading=false
            } ?: run {
                println(res.error)
            }
        }
    }

    fun toggleDialog(f: Boolean?) {
        showDialog = f ?: !showDialog
    }

    fun upData(cls: TodoCls? = null) {
        toggleDialog(true)
        todoItem = if (cls == null) {
            TodoBody("-1")
        } else {
            TodoBody(cls.id, cls.title, cls.content, cls.done)
        }
    }

    fun toggleChecked(cls: TodoCls, f: Boolean) {
        viewModelScope.launch {
            safeService {
                apiService.updateTodo(
                    TodoBody(
                        id = cls.id,
                        title = cls.title,
                        content = cls.content,
                        done = f
                    )
                )
            }.success?.let {
                loadList()
            }
        }
    }

    fun delItem(id: String) {
        viewModelScope.launch {
            safeService { apiService.deleteTodo(id) }.success?.let {
                _toast.emit("已删除$id")
                loadList()
            }
        }
    }

    fun submit(id: String) {
        viewModelScope.launch {
            safeService {
                if (id == "-1") {
                    apiService.createTodo(todoItem)
                } else {
                    apiService.updateTodo(todoItem)
                }
            }.success?.let {
                _toast.emit(if (id == "-1") "${todoItem.title}新增成功" else "修改成功")
                toggleDialog(false)
                loadList()
                todoItem= TodoBody()
            }
        }
    }

    fun updateTodoItem(block: TodoBody.() -> TodoBody) {
        todoItem = todoItem.block()
    }
}