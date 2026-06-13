package com.djx.mulcomposerespect.export

import androidx.compose.runtime.Composable

class CameraPermissionState(
    val granted: Boolean,
    val requestPermission: () -> Unit
)

@Composable
expect fun rememberCameraPermissionState(): CameraPermissionState