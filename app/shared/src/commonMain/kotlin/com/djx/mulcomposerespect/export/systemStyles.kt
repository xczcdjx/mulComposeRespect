package com.djx.mulcomposerespect.export

import androidx.compose.runtime.Composable

expect object SystemStyles {
    fun hideStatusBar()
    fun showStatusBar()
}
@Composable
expect fun SystemBarStyle(isDark: Boolean)