package com.gulsenurgunes.myapplication.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val primaryLight = Color(0xFF56C312)
val onPrimaryLight = Color(0xFFFFFFFF)
val primaryContainerLight = Color(0xFF67EA16)
val onPrimaryContainerLight = Color(0xFF0A6847)
val secondaryLight = Color(0xFFFF9800)
val errorLight = Color(0xFFFCBAAD)
val onErrorLight = Color(0xFFFFFFFF)
val errorContainerLight = Color(0xFFFFDAD6)
val onErrorContainerLight = Color(0xFF410002)
val backgroundLight = Color(0xFFFFFFFF)
val onBackgroundLight = Color(0xFF1B1B21)
val cardBackgroundLight = Color(0xFFEDEDED)
val onCardBackgroundLight = Color(0xFF1B1B21)
val primaryCardBackgroundLight = Color(0xFFE8F5E9)
val outlineLight = Color(0xFF777680)
val textColorLight = Color(0xFF3F3F46)
val cardColorLightGreen = Color(0xffE8F5E9)
val cardColorLightBlue = Color(0xffE3F2FD)
val cardColorLightPink = Color(0xffFCE4EC)
val cardColorLightYellow = Color(0xffFFF9C4)
val buttonColorLight = Color(0xFF73A857)

val primaryDark = Color(0xFF90D26D)
val onPrimaryDark = Color(0xFF000000)
val primaryContainerDark = Color(0xFF73A857)
val onPrimaryContainerDark = Color(0xFF074932)
val secondaryDark = Color(0xFFB26A00)
val errorDark = Color(0xFFFFB4AB)
val onErrorDark = Color(0xFF690005)
val errorContainerDark = Color(0xFF93000A)
val onErrorContainerDark = Color(0xFFFFDAD6)
val backgroundDark = Color(0xFF2C2C2C)
val onBackgroundDark = Color(0xFFE4E1E9)
val cardBackgroundDark = Color(0xFF1E1E1E)
val onCardBackgroundDark = Color(0xFFE4E1E9)
val primaryCardBackgroundDark = Color(0xFF3C5548)
val outlineDark = Color(0xFF91909A)
val textColorDark = Color(0xFFE4E1E9)
val cardColorDarkGreen = Color(0xFF73A857)
val cardColorDarkBlue = Color(0xFF6DA3D2)
val cardColorDarkPink = Color(0xFFD26DA3)
val cardColorDarkYellow = Color(0xFFD2C06D)
val buttonColorDark = Color(0xFF73A857)

fun lightColors(): LLColor = LLColor(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    cardBackground = cardBackgroundLight,
    onCardBackground = onCardBackgroundLight,
    outline = outlineLight,
    textColor = textColorLight,
    primaryCardBackground = primaryCardBackgroundLight,
    cardColorGreen = cardColorLightGreen,
    cardColorYellow = cardColorLightYellow,
    cardColorBlue = cardColorLightBlue,
    cardColorPink = cardColorLightPink,
    buttonColor = buttonColorLight
)

fun darkColors(): LLColor = LLColor(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    cardBackground = cardBackgroundDark,
    onCardBackground = onCardBackgroundDark,
    outline = outlineDark,
    textColor = textColorDark,
    primaryCardBackground = primaryCardBackgroundDark,
    cardColorGreen = cardColorDarkGreen,
    cardColorYellow = cardColorDarkYellow,
    cardColorBlue = cardColorDarkBlue,
    cardColorPink = cardColorDarkPink,
    buttonColor = buttonColorDark
)

data class LLColor(
    val primary: Color,
    val onPrimary: Color,
    val primaryContainer: Color,
    val onPrimaryContainer: Color,
    val secondary: Color,
    val error: Color,
    val onError: Color,
    val errorContainer: Color,
    val onErrorContainer: Color,
    val background: Color,
    val onBackground: Color,
    val cardBackground: Color,
    val onCardBackground: Color,
    val primaryCardBackground: Color,
    val outline: Color,
    val textColor: Color,
    val cardColorGreen: Color,
    val cardColorYellow: Color,
    val cardColorBlue: Color,
    val cardColorPink: Color,
    val buttonColor: Color,
    val white: Color = Color.White,
)

internal val LocalColors = staticCompositionLocalOf { lightColors() }
