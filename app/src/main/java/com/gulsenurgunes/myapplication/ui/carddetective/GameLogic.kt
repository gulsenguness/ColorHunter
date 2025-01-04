package com.gulsenurgunes.myapplication.ui.carddetective

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gulsenurgunes.myapplication.data.detectivecard.DetectiveCard
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
fun DisplayScore(score: Int) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .background(Color.LightGray)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Score: $score",
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
