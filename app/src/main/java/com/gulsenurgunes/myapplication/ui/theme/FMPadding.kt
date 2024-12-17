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
    val dimension16:Dp
)

val Padding=FMPadding(
    dimension1 = 1.dp,
    dimension16=16.dp
)