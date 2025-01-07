package com.gulsenurgunes.myapplication.ui.carddetective

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gulsenurgunes.myapplication.data.detectivecard.DetectiveCard
import com.gulsenurgunes.myapplication.ui.theme.NYTheme.padding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

const val NUM_OF_CARD_SCOR1 = 50
const val NUM_OF_CARD_SCOR2 = 30
const val NUM_OF_CARD_SCOR3 = 10

object GameLogic {
    private val matchedImages = mutableListOf<Int>()

    fun checkForMatch(
        selectedCard: DetectiveCard,
        cards: List<DetectiveCard>,
        currentImageToMatch: Int?,
        viewModel: DetectiveViewModel,
        scope: CoroutineScope = CoroutineScope(Dispatchers.Main)
    ) {
        selectedCard.clickCount++
        viewModel.addSelectedCard(selectedCard)

        if (viewModel.selectedCards.value?.size == 2) {
            handleCardMatch(viewModel, cards, currentImageToMatch)
        } else {
            if (selectedCard.imageId == currentImageToMatch) {
                selectedCard.isFaceUp = true
                val points = when (selectedCard.clickCount) {
                    1 -> NUM_OF_CARD_SCOR1
                    2 -> NUM_OF_CARD_SCOR2
                    else -> NUM_OF_CARD_SCOR3
                }
                viewModel.updateScore(viewModel.score.value?.plus(points) ?: 0)
            } else {
                selectedCard.isFaceUp = false
            }
            viewModel.setCards(cards)
        }
        if (selectedCard.imageId == currentImageToMatch) {
            matchedImages.add(currentImageToMatch)
            scope.launch {
                delay(3000)
                viewModel.nextImageToMatch()
            }
        }
    }

    private fun handleCardMatch(
        viewModel: DetectiveViewModel,
        cards: List<DetectiveCard>,
        currentImageToMatch: Int?
    ) {
        val selectedCards = viewModel.selectedCards.value!!

        if (selectedCards.size == 2) {
            val firstCard = selectedCards[0]
            val secondCard = selectedCards[1]

            if (firstCard.imageId == currentImageToMatch && secondCard.imageId == currentImageToMatch) {
                firstCard.isFaceUp = true
                secondCard.isFaceUp = true
                markCardsAsMatched(listOf(firstCard, secondCard))
            } else {
                firstCard.isFaceUp = false
                secondCard.isFaceUp = false
            }
            viewModel.setCards(cards)
        }
    }

    private fun markCardsAsMatched(cards: List<DetectiveCard>) {
        cards[0].isMatched = true
        cards[1].isMatched = true
    }
}

@Composable
fun DisplayScore(score: Int,progress:Float) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Puan: $score",
            style = MaterialTheme.typography.bodySmall
        )
        LinearProgressIndicator(
            progress = progress.coerceIn(0f, 1f),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
        )
    }
}

