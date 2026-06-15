package com.djx.mulcomposerespect

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.djx.mulcomposerespect.di.startKoin

fun main() = application {
    startKoin()
/*    val windowState = rememberWindowState(
        placement = WindowPlacement.Fullscreen
    )*/
    Window(
        onCloseRequest = ::exitApplication,
        title = "MulComposeRespect",
//        state = windowState
    ) {
        App()
    }
}