package com.gulsenurgunes.myapplication.ui.colorfulpuzzle


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.gulsenurgunes.myapplication.R
import com.gulsenurgunes.myapplication.ui.theme.NYTheme

@Composable
fun ColorfulPuzzle() {
    val cardStates = remember { MutableList(9) { mutableStateOf(false) } }
    val openedCards = remember { mutableStateListOf<Int>() }
    val images = listOf(
        R.drawable.noel1,
        R.drawable.noel1,
        R.drawable.noel2,
        R.drawable.noel2,
        R.drawable.noel3,
        R.drawable.noel3,
        R.drawable.noel4,
        R.drawable.noel4,
        R.drawable.noelhediye,
    ).shuffled()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(NYTheme.padding.dimension16),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(80.dp))
        LottieBellAnimation()
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "Colorful Puzzle",
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(9) { index ->
                FlipCard(
                    isFlipped = cardStates[index].value,
                    imageRes = images[index],
                    onClick = {
                        handleCardClick(index, cardStates, openedCards, images)
                    }
                )
            }
        }

    }
}

@Composable
fun LottieBellAnimation() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.bell))
    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        modifier = Modifier
            .size(84.dp)
            .padding(bottom = 8.dp)
    )
}

private fun handleCardClick(
    index: Int,
    cardStates: List<MutableState<Boolean>>,
    openedCards: MutableList<Int>,
    images: List<Int>
) {
    if (cardStates[index].value || openedCards.size >= 2) return
    cardStates[index].value = true
    openedCards.add(index)
    if (openedCards.size == 2) {
        val firstCard = openedCards[0]
        val secondCard = openedCards[1]
        if (images[firstCard] != images[secondCard]) {
            android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
                cardStates[firstCard].value = false
                cardStates[secondCard].value = false
            }, 1000)
        }
        openedCards.clear()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ColorfulPuzzle()
}