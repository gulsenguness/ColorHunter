package com.gulsenurgunes.myapplication.ui.carddetective

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gulsenurgunes.myapplication.data.DetectiveCard
import com.gulsenurgunes.myapplication.R
import com.gulsenurgunes.myapplication.ui.colorfulpuzzle.flipCard
import com.gulsenurgunes.myapplication.ui.theme.NYTheme.padding

// Deneme
@Composable
fun CardDetective(
    viewModel: DetectiveViewModel
) {
    val cards by viewModel.cards.observeAsState(emptyList())
    val score by viewModel.score.observeAsState(0)
    val imageToMatch by viewModel.imageToMatch.observeAsState(null)

    LaunchedEffect(Unit) {
        initializeGame(viewModel)
    }
    Column(modifier = Modifier.background(Color.White)) {
        DisplayCards(cards, viewModel, imageToMatch,)
        DisplayImageToMatch(imageToMatch)
        DisplayScore(score)
    }
}

private fun initializeGame(
    viewModel: DetectiveViewModel
) {
    val initialCards = generateCards()
    viewModel.setCards(initialCards)
    val randomImage = initialCards.random().imageId
    viewModel.setImageToMatch(randomImage)
}

@Composable
private fun DisplayCards(
    cards: List<DetectiveCard>,
    viewModel: DetectiveViewModel,
    imageToMatch: Int?,
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(padding.dimension16),
        verticalArrangement = Arrangement.spacedBy(padding.dimension16)
    ) {
        if (imageToMatch != null) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
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
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(padding.dimension8),
            verticalArrangement = Arrangement.spacedBy(padding.dimension8)
        ) {
            items(cards.take(9)) { card ->
                DisplayCardImage(card = card)
                flipCard(
                    isFlipped = card.isFaceUp,
                    imageRes = card.imageId,
                    onClick = {
                        if (!card.isMatched && !card.isFaceUp) {
                            checkForMatch(card, cards, imageToMatch, viewModel)
                        }
                    }
                )
            }
        }
    }
}


@Composable
private fun DisplayCardImage(card: DetectiveCard) {
    val cardSize=100.dp
    if (card.isFaceUp) {
        Image(painterResource(id = card.imageId), contentDescription = null, modifier = Modifier.size(cardSize))
    } else {
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color.Gray)
        )
    }
}

@Composable
private fun DisplayImageToMatch(imageToMatch: Int?) {
    imageToMatch?.let {
        Card(
            modifier = Modifier
                .size(100.dp)
                .background(Color.LightGray),
            onClick = {}
        ) {
            Image(painterResource(id = it), contentDescription = null)
        }
    }
}

@Composable
private fun DisplayScore(score: Int) {
    Text("Score: $score", modifier = Modifier.padding(16.dp))
}

fun generateCards(): List<DetectiveCard> {
    val imageIds = listOf(
        R.drawable.noel1,
        R.drawable.noel2,
        R.drawable.noel3,
        R.drawable.noel4,
        R.drawable.noelhediye,
        R.drawable.tree,
        R.drawable.ic_launcher_foreground,
        R.drawable.ic_launcher_background,
        R.drawable.tree
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

fun checkForMatch(
    selectedCard: DetectiveCard,
    cards: List<DetectiveCard>,
    imageToMatch: Int?,
    viewModel: DetectiveViewModel
) {
    viewModel.addSelectedCard(selectedCard)

    if (viewModel.selectedCards.value?.size == 2) {
        handleCardMatch(viewModel, cards)
    } else {
        selectedCard.isFaceUp = true
        viewModel.setCards(cards)
    }

    if (selectedCard.imageId == imageToMatch) {
        viewModel.updateScore(viewModel.score.value?.plus(20) ?: 0)
    }
}

private fun handleCardMatch(
    viewModel: DetectiveViewModel,
    cards: List<DetectiveCard>
) {
    val selectedCards = viewModel.selectedCards.value!!

    if (selectedCards[0].imageId == selectedCards[1].imageId) {
        markCardsAsMatched(selectedCards)
        viewModel.updateScore(viewModel.score.value?.plus(10) ?: 0)
    } else {
        hideSelectedCards(selectedCards)
        viewModel.updateScore(viewModel.score.value?.minus(5) ?: 0)
    }
    viewModel.setCards(cards)
    viewModel.resetSelectedCards()
}

private fun markCardsAsMatched(cards: List<DetectiveCard>) {
    cards[0].isMatched = true
    cards[1].isMatched = true
}

private fun hideSelectedCards(cards: List<DetectiveCard>) {
    cards[0].isFaceUp = false
    cards[1].isFaceUp = false
}


