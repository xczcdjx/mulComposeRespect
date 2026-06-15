package com.djx.mulcomposerespect.export

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State

expect object SystemStyles {
    fun hideStatusBar()
    fun showStatusBar()
    fun toggleStatusBar()
}
@Composable
expect fun SystemBarStyle(isDark: Boolean)

@Composable
expect fun rememberStatusBarVisible(): State<Boolean>