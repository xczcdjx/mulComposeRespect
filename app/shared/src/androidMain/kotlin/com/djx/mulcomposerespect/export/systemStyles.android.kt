package com.djx.mulcomposerespect.export

import android.app.Activity
import android.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

actual object SystemStyles {

    private var activity: Activity? = null
    private var isVisible = true
    fun init(activity: Activity) {
        this.activity = activity
    }

    actual fun hideStatusBar() {
        val act = activity ?: return
        WindowInsetsControllerCompat(
            act.window,
            act.window.decorView
        ).hide(WindowInsetsCompat.Type.statusBars())

        isVisible = false
    }

    actual fun showStatusBar() {
        val act = activity ?: return
        WindowInsetsControllerCompat(
            act.window,
            act.window.decorView
        ).show(WindowInsetsCompat.Type.statusBars())

        isVisible = true
    }

    actual fun toggleStatusBar() {
        if (isVisible) {
            hideStatusBar()
        } else {
            showStatusBar()
        }
    }
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
    val view = LocalView.current
    val visibleState = remember { mutableStateOf(true) }

    DisposableEffect(view) {
        ViewCompat.setOnApplyWindowInsetsListener(view) { _, insets ->
            visibleState.value = insets.isVisible(WindowInsetsCompat.Type.statusBars())
            insets
        }

        ViewCompat.requestApplyInsets(view)

        onDispose {
            ViewCompat.setOnApplyWindowInsetsListener(view, null)
        }
    }

    return visibleState
}