package com.djx.mulcomposerespect.export

import androidx.compose.runtime.Composable
import platform.UIKit.UIApplication
import platform.UIKit.UIStatusBarAnimation
import platform.UIKit.setStatusBarHidden

actual object SystemStyles {

    actual fun hideStatusBar() {
        UIApplication.sharedApplication.setStatusBarHidden(
            true,
            UIStatusBarAnimation.UIStatusBarAnimationFade
        )
    }

    actual fun showStatusBar() {
        UIApplication.sharedApplication.setStatusBarHidden(
            false,
            UIStatusBarAnimation.UIStatusBarAnimationFade
        )
    }
}

@Composable
actual fun SystemBarStyle(isDark: Boolean) {
}