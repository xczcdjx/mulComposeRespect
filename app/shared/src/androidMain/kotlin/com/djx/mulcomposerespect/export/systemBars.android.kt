package com.djx.mulcomposerespect.export

import android.app.Activity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

actual object SystemBars {

    private var activity: Activity? = null

    fun init(activity: Activity) {
        this.activity = activity
    }

    actual fun hide() {
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

    actual fun show() {
        val act = activity ?: return

        WindowInsetsControllerCompat(
            act.window,
            act.window.decorView
        ).show(WindowInsetsCompat.Type.systemBars())
    }
}