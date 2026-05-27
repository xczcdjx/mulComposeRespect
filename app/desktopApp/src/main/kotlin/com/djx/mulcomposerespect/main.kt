package com.djx.mulcomposerespect

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "MulComposeRespect",
    ) {
        App()
    }
}