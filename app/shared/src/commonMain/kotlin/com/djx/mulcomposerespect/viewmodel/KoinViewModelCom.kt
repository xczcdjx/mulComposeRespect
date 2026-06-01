package com.djx.mulcomposerespect.viewmodel

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.annotation.KoinViewModel

@Composable
fun KoinViewmodelCom() {

}

@KoinViewModel
class HomeViewModel(

) : ViewModel() {

    private val _title = MutableStateFlow("Hello Koin")
    val title: StateFlow<String> = _title

    fun load() {

    }
}