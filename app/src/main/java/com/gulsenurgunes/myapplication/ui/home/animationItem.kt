package com.gulsenurgunes.myapplication.ui.home
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.gulsenurgunes.myapplication.ui.theme.NYTheme.padding

@Composable
fun animationItem(
    animationId: Int,
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(animationId))
    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        modifier = Modifier
            .size(padding.dimension84)
            .padding(padding.dimension8)
    )
}
