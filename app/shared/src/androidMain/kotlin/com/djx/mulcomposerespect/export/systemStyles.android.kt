package com.djx.mulcomposerespect.export

import android.app.Activity
import android.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

actual object SystemStyles {

    private var activity: Activity? = null
    private val statusBarVisibleState = mutableStateOf(true)
    fun init(activity: Activity) {
        this.activity = activity
    }

    actual fun hideStatusBar() {
        val act = activity ?: return
        WindowInsetsControllerCompat(
            act.window,
            act.window.decorView
        ).hide(WindowInsetsCompat.Type.statusBars())

        statusBarVisibleState.value = false
    }

    actual fun showStatusBar() {
        val act = activity ?: return
        WindowInsetsControllerCompat(
            act.window,
            act.window.decorView
        ).show(WindowInsetsCompat.Type.statusBars())

        statusBarVisibleState.value = true
    }

    actual fun toggleStatusBar() {
        if (statusBarVisibleState.value) {
            hideStatusBar()
        } else {
            showStatusBar()
        }
    }
    actual fun enterFullScreen() {
        hideStatusBar()
    }

    actual fun exitFullScreen() {
        showStatusBar()
    }

    actual fun toggleFullScreen() {
        if (!statusBarVisibleState.value) exitFullScreen()
        else enterFullScreen()
    }

    fun statusBarState(): State<Boolean> = statusBarVisibleState
}

@Composable
actual fun SystemBarStyle(isDark: Boolean) {
    val view = LocalView.current

    DisposableEffect(isDark) {
        val activity = view.context as Activity
        val window = activity.window

        WindowCompat.setDecorFitsSystemWindows(window, false)

        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT

        WindowInsetsControllerCompat(window, view).apply {
            isAppearanceLightStatusBars = !isDark
            isAppearanceLightNavigationBars = !isDark
        }

        onDispose {}
    }
}

@Composable
actual fun rememberStatusBarVisible(): State<Boolean> {
    return SystemStyles.statusBarState()
}

@Composable
actual fun rememberFullScreen(): State<Boolean> {
    return remember {
        derivedStateOf {
            !SystemStyles.statusBarState().value
        }
    }
}