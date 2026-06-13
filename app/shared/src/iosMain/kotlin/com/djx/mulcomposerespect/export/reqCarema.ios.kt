package com.djx.mulcomposerespect.export

import androidx.compose.runtime.Composable

import androidx.compose.runtime.*
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.camera.CAMERA
import dev.icerock.moko.permissions.compose.BindEffect
import dev.icerock.moko.permissions.compose.rememberPermissionsControllerFactory
import kotlinx.coroutines.launch

@Composable
actual fun rememberCameraPermissionState(): CameraPermissionState {
    val factory = rememberPermissionsControllerFactory()
    val controller = remember(factory) {
        factory.createPermissionsController()
    }

    BindEffect(controller)

    val scope = rememberCoroutineScope()
    var granted by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        granted = controller.isPermissionGranted(Permission.CAMERA)
    }

    return CameraPermissionState(
        granted = granted,
        requestPermission = {
            scope.launch {
                try {
                    controller.providePermission(Permission.CAMERA)
                    granted = true
                } catch (e: Exception) {
                    granted = false
                }
            }
        }
    )
}