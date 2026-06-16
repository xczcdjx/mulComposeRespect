package com.djx.mulcomposerespect

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material.icons.filled.Minimize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowScope
import com.djx.mulcomposerespect.export.SystemStyles
import com.djx.mulcomposerespect.export.rememberFullScreen
import com.djx.mulcomposerespect.titleBar.MacButtonType
import com.djx.mulcomposerespect.titleBar.MacWindowButton

@Composable
fun WindowScope.DesktopTitleBar(
    title: String,
    onClose: () -> Unit,
    onMinimize: () -> Unit = {},
    onMaximize: () -> Unit = {}
) {
    val isDark by SystemStyles.darkStatus
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(36.dp)
            .background(Color.Transparent)
            .padding(start = 12.dp, end = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isMacOs()) {
            MacWindowButtons(
                onClose = onClose,
                onMinimize = onMinimize,
                onMaximize = onMaximize
            )

            Spacer(modifier = Modifier.width(12.dp))

            WindowDraggableArea(
                modifier = Modifier.weight(1f).fillMaxHeight()
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth().fillMaxHeight()
                        .combinedClickable(
                            interactionSource = null,
                            onClick = {},
                            indication = null,
                            onDoubleClick = {
                                SystemStyles.toggleFullScreen()
                            }
                        ),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = title,
                        modifier = Modifier.offset(x = (-56).dp),
                        color = if (isDark) Color.White else Color.Black
                    )
                }
            }

//            Spacer(modifier = Modifier.width(56.dp))
        } else {
            WindowDraggableArea(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .combinedClickable(
                            interactionSource = null,
                            onClick = {},
                            indication = null,
                            onDoubleClick = {
                                SystemStyles.toggleFullScreen()
                            }
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = title,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            IconButton(onClick = onMinimize) {
                Icon(
                    imageVector = Icons.Default.Minimize,
                    contentDescription = "最小化",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            IconButton(onClick = onMaximize) {
                Icon(
                    imageVector = Icons.Default.Fullscreen,
                    contentDescription = "最大化",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            IconButton(onClick = onClose) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "关闭",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Composable
private fun MacWindowButtons(
    onClose: () -> Unit,
    onMinimize: () -> Unit,
    onMaximize: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        MacWindowButton(
            color = Color(0xFFFF5F57),
            type = MacButtonType.Close,
            onClick = onClose
        )

        Spacer(modifier = Modifier.width(8.dp))

        MacWindowButton(
            color = Color(0xFFFFBD2E),
            type = MacButtonType.Minimize,
            onClick = onMinimize
        )

        Spacer(modifier = Modifier.width(8.dp))

        MacWindowButton(
            color = Color(0xFF28C840),
            type = MacButtonType.Maximize,
            onClick = onMaximize,
        )
    }
}

private fun isMacOs(): Boolean {
    return System.getProperty("os.name")
        .contains("Mac", ignoreCase = true)
}