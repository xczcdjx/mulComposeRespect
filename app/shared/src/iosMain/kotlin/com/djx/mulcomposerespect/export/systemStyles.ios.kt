package com.djx.mulcomposerespect.export

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import platform.UIKit.UIApplication
import platform.UIKit.UIStatusBarAnimation
import platform.UIKit.setStatusBarHidden

actual object SystemStyles {
    private val statusBarVisibleState = mutableStateOf(true)
    actual fun hideStatusBar() {
        UIApplication.sharedApplication.setStatusBarHidden(
            true,
            UIStatusBarAnimation.UIStatusBarAnimationFade
        )
        statusBarVisibleState.value = false
    }

    actual fun showStatusBar() {
        UIApplication.sharedApplication.setStatusBarHidden(
            false,
            UIStatusBarAnimation.UIStatusBarAnimationFade
        )
        statusBarVisibleState.value = true
    }

    actual fun toggleStatusBar() {
        if (statusBarVisibleState.value) {
            hideStatusBar()
        } else {
            showStatusBar()
        }
    }

    actual fun enterFullScreen() = hideStatusBar()

    actual fun exitFullScreen() = showStatusBar()

    actual fun toggleFullScreen() {
        if (!statusBarVisibleState.value) exitFullScreen()
        else enterFullScreen()
    }

    fun statusBarState(): State<Boolean> = statusBarVisibleState
}

@Composable
actual fun SystemBarStyle(isDark: Boolean) {
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