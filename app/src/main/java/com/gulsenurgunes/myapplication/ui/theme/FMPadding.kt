package com.gulsenurgunes.myapplication.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

internal val LocalPadding = staticCompositionLocalOf<FMPadding> {
    error("No default padding provided")
}

@Immutable
data class FMPadding(
    val dimension1: Dp,
    val dimension8: Dp,
    val dimension16: Dp,
    val dimension40: Dp,
    val dimension50: Dp,
    val dimension60: Dp,
    val dimension80: Dp,
    val dimension84: Dp,
    val dimension100: Dp,
    val dimension150: Dp,
    val dimension200: Dp,
    val dimension250: Dp,
)

val Padding = FMPadding(
    dimension1 = 1.dp,
    dimension8 = 8.dp,
    dimension16 = 16.dp,
    dimension40 = 40.dp,
    dimension50 = 50.dp,
    dimension60 = 60.dp,
    dimension80 = 80.dp,
    dimension84 = 84.dp,
    dimension100 = 100.dp,
    dimension150 = 150.dp,
    dimension200 = 200.dp,
    dimension250 = 250.dp,
)
