package com.djx.mulcomposerespect

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material.icons.filled.FullscreenExit
import androidx.compose.material.icons.filled.Minimize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowScope
import com.djx.mulcomposerespect.export.SystemStyles
import com.djx.mulcomposerespect.export.rememberFullScreen
import com.djx.mulcomposerespect.titleBar.MacButtons

@Composable
fun WindowScope.DesktopTitleBar(
    title: String,
    onClose: () -> Unit,
    onMinimize: () -> Unit = {},
    onMaximize: () -> Unit = {}
) {
    val isDark by SystemStyles.darkStatus
    val txtColor = if (isDark) Color.White else Color.Black
    val isFull = rememberFullScreen()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(36.dp)
            .background(Color.Transparent)
            .padding(start = 12.dp, end = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isMacOs()) {
            MacButtons(
                onClose = onClose,
                onMinimize = onMinimize,
                onMaximize = onMaximize,
                txtColor,
                isFull=isFull.value
            )

            Spacer(modifier = Modifier.width(12.dp))

            WindowDraggableArea(
                modifier = Modifier.weight(1f).fillMaxHeight()
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth().fillMaxHeight().titleBarDoubleClick {
                        SystemStyles.toggleFullScreen()
                    },
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = title,
                        modifier = Modifier.offset(x = (-56).dp),
                        color = txtColor
                    )
                }
            }

//            Spacer(modifier = Modifier.width(56.dp))
        } else {
            WindowDraggableArea(
                modifier = Modifier.weight(1f).fillMaxHeight()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .fillMaxHeight()
                        .titleBarDoubleClick {
                            SystemStyles.toggleFullScreen()
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = title,
                        color = txtColor
                    )
                }
            }

            IconButton(onClick = onMinimize) {
                Icon(
                    imageVector = Icons.Default.Minimize,
                    modifier = Modifier.offset(y = (-7.5).dp),
                    contentDescription = "最小化",
                    tint = txtColor
                )
            }

            IconButton(onClick = onMaximize) {
                Icon(
                    imageVector = if (isFull.value) Icons.Default.FullscreenExit else Icons.Default.Fullscreen,
                    contentDescription = "最大化",
                    tint = txtColor
                )
            }

            IconButton(onClick = onClose) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "关闭",
                    tint = txtColor
                )
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun Modifier.titleBarDoubleClick(
    onDoubleClick: () -> Unit
): Modifier {
    val lastClickTime = remember { mutableLongStateOf(0L) }

    return this.onPointerEvent(PointerEventType.Press) {
        val now = System.currentTimeMillis()
        if (now - lastClickTime.longValue < 350L) {
            onDoubleClick()
            lastClickTime.longValue = 0L
        } else {
            lastClickTime.longValue = now
        }
    }
}

private fun isMacOs(): Boolean {
    return System.getProperty("os.name")
        .contains("Mac", ignoreCase = true)
}