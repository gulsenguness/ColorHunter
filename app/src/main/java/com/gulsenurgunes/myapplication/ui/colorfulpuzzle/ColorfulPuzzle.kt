package com.gulsenurgunes.myapplication.ui.colorfulpuzzle

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.gulsenurgunes.myapplication.R
import com.gulsenurgunes.myapplication.ui.components.lottieBellAnimation
import com.gulsenurgunes.myapplication.ui.theme.NYTheme.padding

const val NUM_OF_CARD_STATES = 9
const val NUM_OF_COLUMNS = 3

@Composable
fun colorfulPuzzle() {
    val cardStates = remember { MutableList(NUM_OF_CARD_STATES) { mutableStateOf(false) } }
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
            .padding(padding.dimension16),
        verticalArrangement = Arrangement.spacedBy(padding.dimension16),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(padding.dimension80))
        lottieBellAnimation()
        Spacer(modifier = Modifier.height(padding.dimension50))
        Text(
            text = "Colorful Puzzle",
            modifier = Modifier.padding(padding.dimension16)
        )
        Spacer(modifier = Modifier.height(padding.dimension8))
        LazyVerticalGrid(
            columns = GridCells.Fixed(NUM_OF_COLUMNS),
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(padding.dimension8),
            verticalArrangement = Arrangement.spacedBy(padding.dimension8)
        ) {
            items(NUM_OF_CARD_STATES) { index ->
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
    colorfulPuzzle()
}
