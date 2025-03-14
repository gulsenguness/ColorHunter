package com.gulsenurgunes.myapplication.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.gulsenurgunes.myapplication.R
import com.gulsenurgunes.myapplication.ui.theme.NYTheme.padding

@Composable
fun lottieBellAnimation() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.bell))
    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        modifier = Modifier
            .size(padding.dimension84)
            .padding(padding.dimension8)
    )
}
