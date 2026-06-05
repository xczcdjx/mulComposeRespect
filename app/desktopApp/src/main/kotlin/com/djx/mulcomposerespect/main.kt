package com.djx.mulcomposerespect

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.djx.mulcomposerespect.di.initKoin

fun main() = application {
    initKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "MulComposeRespect",
    ) {
        App()
    }
}