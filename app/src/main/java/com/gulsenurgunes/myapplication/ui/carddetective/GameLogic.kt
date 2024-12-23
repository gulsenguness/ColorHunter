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
import com.gulsenurgunes.myapplication.data.DetectiveCard

object GameLogic {
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