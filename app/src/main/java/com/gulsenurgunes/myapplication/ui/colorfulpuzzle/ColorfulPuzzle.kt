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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gulsenurgunes.myapplication.R
import com.gulsenurgunes.myapplication.ui.components.LottieSnow
import com.gulsenurgunes.myapplication.ui.theme.NYTheme.padding

const val NUM_OF_CARD_STATES = 9
const val NUM_OF_COLUMNS = 3

@Composable
fun colorfulPuzzle() {
    val cardStates = remember { MutableList(NUM_OF_CARD_STATES) { mutableStateOf(false) } }
    val openedCards = remember { mutableStateListOf<Int>() }
    val images = getShuffledImages()
    var score by remember { mutableStateOf(0) }
    var progress by remember { mutableStateOf(0f) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding.dimension16),
        verticalArrangement = Arrangement.spacedBy(padding.dimension16),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(padding.dimension80))
        LottieSnow(
            modifier = Modifier
                .size(200.dp)
        )
        Spacer(modifier = Modifier.height(padding.dimension16))
        Text(
            text = "Colorful Puzzle",
            modifier = Modifier.padding(padding.dimension16)
        )
        ScoreAndProgreddDection(score = score, progress = progress)
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
                        handleCardClick(
                            index,
                            cardStates,
                            openedCards,
                            images,
                            { score++ },
                            { progress += 1f / NUM_OF_CARD_STATES })
                    }
                )
            }
        }
    }
}



