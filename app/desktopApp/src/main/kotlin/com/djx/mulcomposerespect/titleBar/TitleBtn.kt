package com.djx.mulcomposerespect.titleBar

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.unit.dp

enum class MacButtonType {
    Close,
    Minimize,
    Maximize
}

@Composable
fun MacButtons(
    onClose: () -> Unit,
    onMinimize: () -> Unit,
    onMaximize: () -> Unit,
    txtColor: Color = Color.Black,
    isFull: Boolean = false
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        CanvasButton(
            color = Color(0xFFFF5F57),
            type = MacButtonType.Close,
            onClick = onClose,
            txtColor
        )

        Spacer(modifier = Modifier.width(8.dp))

        CanvasButton(
            color = Color(0xFFFFBD2E),
            type = MacButtonType.Minimize,
            onClick = onMinimize,
            txtColor
        )

        Spacer(modifier = Modifier.width(8.dp))

        CanvasButton(
            color = Color(0xFF28C840),
            type = MacButtonType.Maximize,
            onClick = onMaximize,
            txtColor,
            isFull
        )
    }
}

@Composable
fun CanvasButton(
    color: Color,
    type: MacButtonType,
    onClick: () -> Unit,
    txtColor: Color = Color.Black,
    isFull: Boolean = false
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    Box(
        modifier = Modifier
            .size(18.dp)
            .hoverable(interactionSource)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier.size(14.dp)
        ) {
            drawCircle(color = color)

            if (isHovered) {
                val strokeWidth = 1.4.dp.toPx()

                when (type) {
                    MacButtonType.Close -> {
                        drawLine(
                            color = txtColor,
                            start = Offset(size.width * 0.32f, size.height * 0.32f),
                            end = Offset(size.width * 0.68f, size.height * 0.68f),
                            strokeWidth = strokeWidth,
                            cap = StrokeCap.Round
                        )
                        drawLine(
                            color = txtColor,
                            start = Offset(size.width * 0.68f, size.height * 0.32f),
                            end = Offset(size.width * 0.32f, size.height * 0.68f),
                            strokeWidth = strokeWidth,
                            cap = StrokeCap.Round
                        )
                    }

                    MacButtonType.Minimize -> {
                        drawLine(
                            color = txtColor,
                            start = Offset(size.width * 0.28f, size.height * 0.50f),
                            end = Offset(size.width * 0.72f, size.height * 0.50f),
                            strokeWidth = strokeWidth,
                            cap = StrokeCap.Round
                        )
                    }

                    MacButtonType.Maximize -> {
                        if (isFull) {
                            drawExitFullScreenTriangles(txtColor)
                        } else {
                            drawEnterFullScreenTriangles(txtColor)
                        }
                    }
                }
            }
        }
    }
}


private fun DrawScope.drawEnterFullScreenTriangles(
    iconColor: Color
) {
    val rightBottom = Path().apply {
        moveTo(size.width * 0.70f, size.height * 0.70f)
        lineTo(size.width * 0.48f, size.height * 0.70f)
        lineTo(size.width * 0.70f, size.height * 0.48f)
        close()
    }
    val leftTop = Path().apply {
        moveTo(size.width * 0.30f, size.height * 0.30f)
        lineTo(size.width * 0.52f, size.height * 0.30f)
        lineTo(size.width * 0.30f, size.height * 0.52f)
        close()
    }
    drawPath(leftTop, iconColor)
    drawPath(rightBottom, iconColor)
}

private fun DrawScope.drawExitFullScreenTriangles(
    iconColor: Color
) {
    /*val leftBottom = Path().apply {
    moveTo(size.width * 0.30f, size.height * 0.70f)
    lineTo(size.width * 0.30f, size.height * 0.48f)
    lineTo(size.width * 0.52f, size.height * 0.70f)
    close()
}

// 右上角三角形：指向右上
val rightTop = Path().apply {
    moveTo(size.width * 0.70f, size.height * 0.30f)
    lineTo(size.width * 0.48f, size.height * 0.30f)
    lineTo(size.width * 0.70f, size.height * 0.52f)
    close()
}*/
    val rightBottom = Path().apply {
        moveTo(size.width * 0.70f, size.height * 0.70f)
        lineTo(size.width * 0.48f, size.height * 0.70f)
        lineTo(size.width * 0.70f, size.height * 0.48f)
        close()
    }
    val leftTop = Path().apply {
        moveTo(size.width * 0.30f, size.height * 0.30f)
        lineTo(size.width * 0.52f, size.height * 0.30f)
        lineTo(size.width * 0.30f, size.height * 0.52f)
        close()
    }
    translate(left = 3.2f, top = 3.2f) {
        drawPath(leftTop, iconColor)
    }
    translate(left = -3.2f, top = -3.2f) {
        drawPath(rightBottom, iconColor)
    }
}