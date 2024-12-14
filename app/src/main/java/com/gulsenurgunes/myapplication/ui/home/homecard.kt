package com.gulsenurgunes.myapplication.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import androidx.compose.foundation.clickable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import com.airbnb.lottie.compose.LottieAnimation


@Composable
fun HomeCard(
    modifier: Modifier = Modifier,
    animationId:Int,
    title: String,
    onClick: () -> Unit,
    cardColor: Color = Color.Red
) {
    Card(
        modifier = modifier
            .height(150.dp)
            .clickable { onClick() }
            .background(cardColor),
        shape = MaterialTheme.shapes.medium,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(animationId))
            val progress by animateLottieCompositionAsState(composition)
            LottieAnimation(
                composition = composition,
                progress =progress,
                modifier=Modifier
                    .size(84.dp)
                    .padding(bottom = 8.dp)
            )

            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                )
        }
    }
}
