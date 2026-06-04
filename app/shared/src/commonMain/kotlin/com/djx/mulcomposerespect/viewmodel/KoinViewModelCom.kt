package com.djx.mulcomposerespect.viewmodel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinViewModel

@Composable
fun KoinViewmodelCom(
    vm: HomeViewModel = koinViewModel()
) {
    LaunchedEffect(Unit) {
        vm.toast.collect { msg -> print("Toast: $msg") }
    }
    val count by vm.count.collectAsState()
    val title by vm.title.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(title)
            })
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Text("count $count")
            TextButton({
                vm.add(1)
            }) {
                Text("c++")
            }
        }
    }
}

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