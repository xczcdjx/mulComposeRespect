package com.djx.mulcomposerespect.viewmodel


import androidx.lifecycle.ViewModel
import com.djx.mulcomposerespect.services.ApiService
import com.djx.mulcomposerespect.app.AppState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.android.annotation.KoinViewModel


@KoinViewModel
class HomeViewModel(
    private val appState: AppState,
    private val apiService: ApiService
) : ViewModel() {
    private val _title = MutableStateFlow("Storage Test")
    private var _toast = MutableSharedFlow<String>()
    val title=_title.asStateFlow()
    val toast=_toast.asSharedFlow()
}