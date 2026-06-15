package com.djx.mulcomposerespect.export

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State

actual object SystemStyles {
    actual fun hideStatusBar() {
    }

    actual fun showStatusBar() {
    }
    actual fun toggleStatusBar() {
    }
}

@Composable
actual fun SystemBarStyle(isDark: Boolean) {
}

@Composable
actual fun rememberStatusBarVisible(): State<Boolean> {
    TODO("Not yet implemented")
}