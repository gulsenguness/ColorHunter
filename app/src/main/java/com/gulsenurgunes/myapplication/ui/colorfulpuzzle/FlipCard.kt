package com.gulsenurgunes.myapplication.ui.colorfulpuzzle

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.gulsenurgunes.myapplication.R
import com.gulsenurgunes.myapplication.ui.theme.NYTheme.padding

@Composable
fun FlipCard(isFlipped: Boolean, imageRes: Int, onClick: () -> Unit) {
    val rotation = remember { Animatable(0f) }

    LaunchedEffect(isFlipped) {
        rotation.animateTo(
            targetValue = if (isFlipped) 180f else 0f,
            animationSpec = tween(durationMillis = 400, easing = LinearEasing)
        )
    }

    val frontVisible = rotation.value <= 90f
    val backVisible = rotation.value > 90f

    Box(
        modifier = Modifier
            .size(padding.dimension100)
            .graphicsLayer {
                rotationY = rotation.value
                cameraDistance = 8 * density
            }
            .background(color = Color.Red)
            .clickable { onClick() }
    ) {
        if (frontVisible) {
            Card(
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.tree),
                        contentDescription = null,
                        modifier = Modifier.size(padding.dimension60)
                    )
                }
            }
        } else if (backVisible) {
            Card(
                modifier = Modifier.fillMaxSize()
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}

fun getShuffledImages() = listOf(
    R.drawable.noel1,
    R.drawable.noel1,
    R.drawable.noel2,
    R.drawable.noel2,
    R.drawable.noel3,
    R.drawable.noel3,
    R.drawable.noel4,
    R.drawable.noel4,
    R.drawable.noelhediye,
)// bu karışttırma işlemi yapıyor .shuffled()
