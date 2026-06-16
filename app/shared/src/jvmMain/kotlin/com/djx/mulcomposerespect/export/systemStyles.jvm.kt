package com.djx.mulcomposerespect.export

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

actual object SystemStyles {
    private val fullScreenState = mutableStateOf(false)
    private val statusBarVisibleState = mutableStateOf(true)

    val darkStatus = mutableStateOf(false)

    private var enterFullScreenAction: (() -> Unit)? = null
    private var exitFullScreenAction: (() -> Unit)? = null

    fun bindFullScreenActions(
        enter: () -> Unit,
        exit: () -> Unit
    ) {
        enterFullScreenAction = enter
        exitFullScreenAction = exit
    }

    fun syncFullScreenState(value: Boolean) {
        fullScreenState.value = value
    }

    actual fun hideStatusBar() {
        statusBarVisibleState.value = false
    }

    actual fun showStatusBar() {
        statusBarVisibleState.value = true
    }

    actual fun toggleStatusBar() {
        statusBarVisibleState.value = !statusBarVisibleState.value
    }

    actual fun enterFullScreen() {
        enterFullScreenAction?.invoke()
        fullScreenState.value = true
    }

    actual fun exitFullScreen() {
        exitFullScreenAction?.invoke()
        fullScreenState.value = false
    }

    actual fun toggleFullScreen() {
        if (fullScreenState.value) {
            exitFullScreen()
        } else {
            enterFullScreen()
        }
    }

    fun statusBarVisibleState(): State<Boolean> = statusBarVisibleState

    fun fullScreenState(): State<Boolean> = fullScreenState


}

@Composable
actual fun SystemBarStyle(isDark: Boolean) {
    SystemStyles.darkStatus.value = isDark
}

@Composable
actual fun rememberStatusBarVisible(): State<Boolean> {
    return SystemStyles.statusBarVisibleState()
}

@Composable
actual fun rememberFullScreen(): State<Boolean> {
    return SystemStyles.fullScreenState()
}