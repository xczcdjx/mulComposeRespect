package com.djx.mulcomposerespect


import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment

import androidx.compose.ui.tooling.preview.Preview
import com.djx.mulcomposerespect.router.Router
import com.djx.mulcomposerespect.utils.ToastManager
import com.dokar.sonner.Toaster
import com.dokar.sonner.rememberToasterState


@Composable
@Preview
fun App() {
    MaterialTheme {
        Surface {
            val toaster = rememberToasterState()
            LaunchedEffect(Unit) {
                ToastManager.toast.collect { toastEvent ->
                    toaster.show(
                        message = toastEvent.message,
                        id = toastEvent.id,
                        icon = toastEvent.icon,
                        action = toastEvent.actionBuilder?.invoke(toaster),
                        type = toastEvent.type,
                        duration = toastEvent.duration
                    )
                }
            }
            Box() {
                Router()
                Toaster(state = toaster, alignment = Alignment.TopCenter)
            }
        }
    }
}