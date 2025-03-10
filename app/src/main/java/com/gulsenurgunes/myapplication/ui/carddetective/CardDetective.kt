package com.gulsenurgunes.myapplication.ui.carddetective

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gulsenurgunes.myapplication.R
import com.gulsenurgunes.myapplication.data.detectivecard.DetectiveCard
import com.gulsenurgunes.myapplication.ui.carddetective.GameLogic.checkForMatch
import com.gulsenurgunes.myapplication.ui.christmaspassword.PuzzleGameScreen
import com.gulsenurgunes.myapplication.ui.colorfulpuzzle.FlipCard
import com.gulsenurgunes.myapplication.ui.components.LottieSnow
import com.gulsenurgunes.myapplication.ui.components.lottieBellAnimation
import com.gulsenurgunes.myapplication.ui.theme.NYTheme.padding
import kotlinx.coroutines.delay

@Composable
fun CardDetective(
    viewModel: DetectiveViewModel
) {
    val cards by viewModel.cards.observeAsState(emptyList())
    val score by viewModel.score.observeAsState(0)
    val imageToMatch by viewModel.imageToMatch.observeAsState(null)
    val isGameReady by viewModel.isGameReady.observeAsState(false)
    val progress = score / 100f

    LaunchedEffect(Unit) {
        initializeGame(viewModel)
        delay(5000)
        viewModel.setGameReady(true)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(padding.dimension16)
    ) {
        DisplayHeader()
        Spacer(modifier = Modifier.height(padding.dimension16))
        DisplayScore(score, progress = progress)
        DisplayCards(cards, viewModel, imageToMatch, isGameReady)
        DisplayImageToMatch(viewModel)
    }
}

const val NUM_OF_CARD_STATES = 9
const val NUM_OF_COLUMNS = 3
@Composable
private fun DisplayHeader() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        LottieSnow(
            modifier = Modifier.size(200.dp)
        )
        Text(
            text = "Card Detective",
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }
}

@Composable
private fun DisplayCards( // How it should look when the cards are face down and face down
    cards: List<DetectiveCard>,
    viewModel: DetectiveViewModel,
    imageToMatch: Int?,
    isGameReady: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding.dimension16),
        verticalArrangement = Arrangement.spacedBy(padding.dimension16)
    ) {

        if (imageToMatch != null) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2f)
                    .padding(bottom = padding.dimension16),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = imageToMatch),
                    contentDescription = "Image to Match",
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(1f)
                )
            }
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(NUM_OF_COLUMNS),
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(padding.dimension8),
            verticalArrangement = Arrangement.spacedBy(padding.dimension8)
        ) {
            items(cards.take(NUM_OF_CARD_STATES)) { card ->
                if (isGameReady) {
                    FlipCard(
                        isFlipped = card.isFaceUp,
                        imageRes = card.imageId,
                        onClick = {
                            if (!card.isMatched && !card.isFaceUp) {
                                checkForMatch(card, cards, imageToMatch, viewModel)
                            }
                        }
                    )
                } else {
                    DisplayCardImage(card.copy(isFaceUp = true))
                }
            }
        }
    }
}

@Composable
private fun DisplayCardImage(card: DetectiveCard) {
    if (card.isFaceUp) {
        Image(
            painterResource(id = card.imageId),
            contentDescription = null,
            modifier = Modifier.size(80.dp)
        )
    } else {
        Box(
            modifier = Modifier
                .size(80.dp)
                .background(Color.Gray)
        )
    }
}

private fun initializeGame(viewModel: DetectiveViewModel) {
    val initialCards = generateCards()
    viewModel.setCards(initialCards)
    val usedImages = viewModel.usedImages.toMutableSet()
    val availableImages = initialCards.map { it.imageId }.filter { it !in usedImages }
    if (availableImages.isNotEmpty()) {
        val randomImage = availableImages.random()
        usedImages.add(randomImage)
        viewModel.setImageToMatch(randomImage)
    } else {
        viewModel.setImageToMatch(null)
    }

    viewModel.setUsedImages(usedImages)
}

@Composable
fun DisplayImageToMatch(viewModel: DetectiveViewModel) {
    val imageToMatch by viewModel.imageToMatch.observeAsState()

    imageToMatch?.let {
        Card(
            modifier = Modifier
                .size(80.dp)
                .padding(8.dp)
                .clip(RoundedCornerShape(16.dp))
                .shadow(8.dp, RoundedCornerShape(16.dp)),
            onClick = {}
        ) {
            Image(
                painter = painterResource(id = it),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
        }
    } ?: Text(
        text = "Oyun bitti! 🎉",
        modifier = Modifier.padding(16.dp)
    )
}

fun generateCards(): List<DetectiveCard> {
    val imageIds = listOf(
        R.drawable.noel1,
        R.drawable.noel2,
        R.drawable.noel3,
        R.drawable.noel4,
        R.drawable.noelhediye,
        R.drawable.tree,
        R.drawable.noel7,
        R.drawable.noel9,
        R.drawable.noel8
    )
    return imageIds.shuffled().map {
        DetectiveCard(
            imageId = it,
            isFaceUp = false,
            isMatched = false,
            level = 1,
        )
    }
}
