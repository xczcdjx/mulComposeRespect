package com.djx.mulcomposerespect.viewmodel.scan

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.touchlab.kermit.Logger
import com.djx.mulcomposerespect.export.rememberCameraPermissionState
import com.djx.mulcomposerespect.utils.ToastManager
import com.dokar.sonner.ToastType
import kotlinx.coroutines.launch
import org.ncgroup.kscan.BarcodeFormat
import org.ncgroup.kscan.BarcodeResult
import org.ncgroup.kscan.ScannerView

@Composable
fun ScanPage(back: () -> Unit = {}) {
    val scope = rememberCoroutineScope()
    Scaffold(topBar = {
        TopAppBar(navigationIcon = {
            IconButton({
                back()
            }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, null)
            }
        }, title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text("Scan")
            }
        })
    }) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            val cameraPermission = rememberCameraPermissionState()
            Column {
                if (cameraPermission.granted) {
                    ScannerView(
                        codeTypes = listOf(BarcodeFormat.FORMAT_ALL_FORMATS)
                    ) { result ->
                        when (result) {
                            is BarcodeResult.OnSuccess -> {
                                val scanText = "扫码成功: " + result.barcode.data
                                scope.launch {
                                    ToastManager.show(scanText, type = ToastType.Success)
                                }
                                Logger.e {
                                    scanText
                                }
                            }

                            is BarcodeResult.OnFailed -> {
                                val str = "扫码失败: ${result.exception.message}"
                                scope.launch {
                                    ToastManager.show(str, type = ToastType.Error)
                                }
                                Logger.e {
                                    str
                                }
                            }

                            BarcodeResult.OnCanceled -> {
                                scope.launch {
                                    ToastManager.show("Cancel", type = ToastType.Warning)
                                    back()
                                }
                            }
                        }
                    }
                } else {
                    Button(
                        onClick = cameraPermission.requestPermission
                    ) {
                        Text("申请相机权限")
                    }
                }
            }
        }
    }
}