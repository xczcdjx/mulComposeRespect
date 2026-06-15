package com.djx.mulcomposerespect.export

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import platform.UIKit.UIApplication
import platform.UIKit.UIStatusBarAnimation
import platform.UIKit.setStatusBarHidden

actual object SystemStyles {
    private val visibleState = mutableStateOf(true)
    actual fun hideStatusBar() {
        UIApplication.sharedApplication.setStatusBarHidden(
            true,
            UIStatusBarAnimation.UIStatusBarAnimationFade
        )
        visibleState.value = false
    }

    actual fun showStatusBar() {
        UIApplication.sharedApplication.setStatusBarHidden(
            false,
            UIStatusBarAnimation.UIStatusBarAnimationFade
        )
        visibleState.value = true
    }
    actual fun toggleStatusBar() {
        if (visibleState.value) {
            hideStatusBar()
        } else {
            showStatusBar()
        }
    }

    fun state(): State<Boolean> = visibleState
}

@Composable
actual fun SystemBarStyle(isDark: Boolean) {
}

@Composable
actual fun rememberStatusBarVisible(): State<Boolean> {
    return SystemStyles.state()
}