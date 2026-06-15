package com.djx.mulcomposerespect.export

import android.app.Activity
import android.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

actual object SystemStyles {

    private var activity: Activity? = null

    fun init(activity: Activity) {
        this.activity = activity
    }

    actual fun hideStatusBar() {
        val act = activity ?: return

        WindowCompat.setDecorFitsSystemWindows(act.window, false)

        WindowInsetsControllerCompat(
            act.window,
            act.window.decorView
        ).apply {
            hide(WindowInsetsCompat.Type.systemBars())

            systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    actual fun showStatusBar() {
        val act = activity ?: return

        WindowInsetsControllerCompat(
            act.window,
            act.window.decorView
        ).show(WindowInsetsCompat.Type.systemBars())
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