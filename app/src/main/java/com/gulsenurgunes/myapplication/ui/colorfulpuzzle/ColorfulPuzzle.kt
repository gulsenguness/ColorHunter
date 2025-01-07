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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gulsenurgunes.myapplication.ui.components.LottieSnow
import com.gulsenurgunes.myapplication.ui.theme.NYTheme.padding

const val NUM_OF_CARD_STATES = 9
const val NUM_OF_COLUMNS = 3

@Composable
fun ColorfulPuzzle(viewModel: GameViewModel = viewModel()) {
    val cardStates = viewModel.cardStates
    val images = viewModel._images
    val score = viewModel.score.intValue
    val progress = viewModel.progress.floatValue
    val elapsedTime = viewModel.elapsedTime.intValue

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding.dimension16),
        verticalArrangement = Arrangement.spacedBy(padding.dimension16),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(score, progress, elapsedTime)
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
                    onClick = { viewModel.onCardClick(index) }
                )
            }
        }
    }
}

@Composable
fun Header(score: Int, progress: Float, elapsedTime: Int) {
    LottieSnow(modifier = Modifier.size(padding.dimension200))
    Text(text = "Colorful Puzzle", modifier = Modifier.padding(padding.dimension16))
    ScoreAndProgreddDection(score = score, progress = progress, elepsedTime = elapsedTime)
}
