package com.djx.mulcomposerespect.export

import androidx.compose.runtime.Composable

import androidx.compose.runtime.remember

@Composable
actual fun rememberCameraPermissionState(): CameraPermissionState {
    return remember {
        CameraPermissionState(
            granted = true,
            requestPermission = {}
        )
    }
}