package com.djx.mulcomposerespect.export

import platform.UIKit.UIApplication
import platform.UIKit.UIStatusBarAnimation
import platform.UIKit.setStatusBarHidden

actual object SystemBars {

    actual fun hide() {
        UIApplication.sharedApplication.setStatusBarHidden(
            true,
            UIStatusBarAnimation.UIStatusBarAnimationFade
        )
    }

    actual fun show() {
        UIApplication.sharedApplication.setStatusBarHidden(
            false,
            UIStatusBarAnimation.UIStatusBarAnimationFade
        )
    }
}