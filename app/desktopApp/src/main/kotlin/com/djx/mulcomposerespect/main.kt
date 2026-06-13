package com.djx.mulcomposerespect

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.djx.mulcomposerespect.di.startKoin

fun main() = application {
    startKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "MulComposeRespect",
    ) {
        App()
    }
}