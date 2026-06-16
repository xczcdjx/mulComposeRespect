package com.djx.mulcomposerespect

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.djx.mulcomposerespect.di.startKoin
import com.djx.mulcomposerespect.export.SystemStyles
import kotlinx.coroutines.flow.collectLatest

fun main() = application {
    startKoin()
    val windowState = rememberWindowState()
    LaunchedEffect(Unit) {
        SystemStyles.bindFullScreenActions(
            enter = {
//                windowState.placement = WindowPlacement.Maximized
                windowState.placement = WindowPlacement.Fullscreen
            },
            exit = {
                windowState.placement = WindowPlacement.Floating
            }
        )
    }
    LaunchedEffect(windowState) {
        snapshotFlow { windowState.placement }
            .collectLatest { placement ->
                SystemStyles.syncFullScreenState(
                    placement == WindowPlacement.Maximized ||
                            placement == WindowPlacement.Fullscreen
                )
            }
    }
    Window(
        onCloseRequest = ::exitApplication,
        title = "MulComposeRespect",
        state = windowState
    ) {
        App()
    }
}