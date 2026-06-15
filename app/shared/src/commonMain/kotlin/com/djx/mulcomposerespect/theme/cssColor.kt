package com.djx.mulcomposerespect.theme


import androidx.compose.ui.graphics.Color
import com.github.ajalt.colormath.parseOrNull
import com.github.ajalt.colormath.Color as MathColor

fun cssColor(
    value: String?,
    default: Color = Color.Unspecified
): Color {
    if (value.isNullOrBlank()) return default
    val rgb = MathColor.parseOrNull(value)?.toSRGB() ?: return default

    return Color(
        red = rgb.r,
        green = rgb.g,
        blue = rgb.b,
        alpha = rgb.alpha
    )
}