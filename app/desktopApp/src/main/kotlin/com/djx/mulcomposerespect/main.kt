package com.djx.mulcomposerespect

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import androidx.compose.ui.zIndex
import com.djx.mulcomposerespect.di.startKoin
import com.djx.mulcomposerespect.export.SystemStyles
import kotlinx.coroutines.flow.collectLatest

fun main() {
    application {
        startKoin()
        val windowState = rememberWindowState()
        LaunchedEffect(Unit) {
            SystemStyles.bindFullScreenActions(enter = {
                windowState.placement = WindowPlacement.Maximized
            }, exit = {
                windowState.placement = WindowPlacement.Floating
            })
        }
        LaunchedEffect(windowState) {
            snapshotFlow { windowState.placement }.collectLatest { placement ->
                SystemStyles.syncFullScreenState(
                    placement == WindowPlacement.Maximized || placement == WindowPlacement.Fullscreen
                )
            }
        }
        Window(
            onCloseRequest = ::exitApplication,
            title = "MulComposeRespect",
            state = windowState,
            undecorated = true,
            transparent = true
        ) {
            Surface(
                shape = RoundedCornerShape(12.dp),
            ) {
                Box() {
                    Row(
                        modifier = Modifier.zIndex(1f)
                    ) {
                        DesktopTitleBar(
                            title = "MulComposeRespect",
                            onClose = ::exitApplication,
                            onMinimize = {
                                windowState.isMinimized = true
                            },
                            onMaximize = {
                                windowState.placement =
                                    if (windowState.placement == WindowPlacement.Fullscreen || windowState.placement == WindowPlacement.Maximized) {
                                        WindowPlacement.Floating
                                    } else {
                                        WindowPlacement.Maximized
                                    }
                            }
                        )
                    }
                    App()
                }
            }
        }
    }
}


