package com.djx.mulcomposerespect

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material.icons.filled.Minimize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowScope

@Composable
fun WindowScope.DesktopTitleBar(
    title: String,
    onClose: () -> Unit,
    onMinimize: () -> Unit = {},
    onMaximize: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(36.dp)
            .background(Color.Transparent)
            .padding(start = 12.dp, end = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isMacOs()){

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
                    modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = title,
                        color = MaterialTheme.colors.onSurface
                    )
                }
            }

            Spacer(modifier = Modifier.width(56.dp))
        }
        else {
            WindowDraggableArea(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = title,
                        color = MaterialTheme.colors.onSurface
                    )
                }
            }

            IconButton(onClick = onMinimize) {
                Icon(
                    imageVector = Icons.Default.Minimize,
                    contentDescription = "最小化",
                    tint = MaterialTheme.colors.onSurface
                )
            }

            IconButton(onClick = onMaximize) {
                Icon(
                    imageVector = Icons.Default.Fullscreen,
                    contentDescription = "最大化",
                    tint = MaterialTheme.colors.onSurface
                )
            }

            IconButton(onClick = onClose) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "关闭",
                    tint = MaterialTheme.colors.onSurface
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
            onClick = onClose
        )

        Spacer(modifier = Modifier.width(8.dp))

        MacWindowButton(
            color = Color(0xFFFFBD2E),
            onClick = onMinimize
        )

        Spacer(modifier = Modifier.width(8.dp))

        MacWindowButton(
            color = Color(0xFF28C840),
            onClick = onMaximize
        )
    }
}
@Composable
private fun MacWindowButton(
    color: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(14.dp)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier.size(12.dp)
        ) {
            drawCircle(color = color)
        }
    }
}
private fun isMacOs(): Boolean {
    return System.getProperty("os.name")
        .contains("Mac", ignoreCase = true)
}