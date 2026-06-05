package com.djx.mulcomposerespect

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.djx.mulcomposerespect.di.KoinInitializer

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    KoinInitializer.start()
    ComposeViewport {
        App()
    }
}