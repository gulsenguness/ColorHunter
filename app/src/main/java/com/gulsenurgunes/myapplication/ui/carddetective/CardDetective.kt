package com.gulsenurgunes.myapplication.ui.carddetective

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gulsenurgunes.myapplication.DetectiveCard
import com.gulsenurgunes.myapplication.DetectiveViewModel
import com.gulsenurgunes.myapplication.R


@Composable
fun CardDetective(viewModel: DetectiveViewModel) {
    val cards by viewModel.cards.observeAsState(emptyList())
    val score by viewModel.score.observeAsState(0)
    val imageToMatch by viewModel.imageToMatch.observeAsState(null)

    LaunchedEffect(Unit) {
        val initialCards = generateCards()
        viewModel.setCards(initialCards)
        val randomImage = initialCards.random().imageId
        viewModel.setImageToMatch(randomImage)
    }

    Column(modifier = Modifier.background(Color.White)) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.weight(1f)
        ) {
            items(cards.take(9)) { card ->
                Card(
                    onClick = {
                        if (!card.isMatched && !card.isFaceUp) {
                            card.isFaceUp = true
                            checkForMatch(card, cards, imageToMatch, viewModel)
                        }
                    }
                ) {
                    if (card.isFaceUp) {
                        Image(painterResource(id = card.imageId), contentDescription = null)
                    } else {
                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .background(Color.Gray)
                        )
                    }
                }
            }
        }

        imageToMatch?.let {
            Card(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.LightGray),
                onClick = {
                }
            ) {
                Image(painterResource(id = it), contentDescription = null)
            }
        }

        Text("Score: $score", modifier = Modifier.padding(16.dp))
    }
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
    val shuffledCards = imageIds.shuffled()
    return shuffledCards.map {
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
        val selectedCards = viewModel.selectedCards.value!!

        if (selectedCards[0].imageId == selectedCards[1].imageId) {
            selectedCards[0].isMatched = true
            selectedCards[1].isMatched = true
            viewModel.updateScore(viewModel.score.value?.plus(10) ?: 0)
        } else {
            selectedCards[0].isFaceUp = false
            selectedCards[1].isFaceUp = false
            viewModel.updateScore(viewModel.score.value?.minus(5) ?: 0)
        }
        viewModel.setCards(cards)

        viewModel.resetSelectedCards()
    } else {
        selectedCard.isFaceUp = true
        viewModel.setCards(cards)
    }
    if (selectedCard.imageId == imageToMatch) {
        viewModel.updateScore(viewModel.score.value?.plus(20) ?: 0) // Bonus puan
    }
}



