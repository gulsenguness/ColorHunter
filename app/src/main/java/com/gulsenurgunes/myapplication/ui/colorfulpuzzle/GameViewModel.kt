package com.gulsenurgunes.myapplication.ui.colorfulpuzzle

import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    val cardStates = MutableList(NUM_OF_CARD_STATES) { mutableStateOf(false) }
    private val matchedCards = mutableStateListOf<Int>() // Eşleşmiş kartları takip eder
    private val openedCards = mutableStateListOf<Int>()
    var score = mutableIntStateOf(0)
    var progress = mutableFloatStateOf(0f)
    var elapsedTime = mutableIntStateOf(0)
    var _images = getShuffledImages()

    init {
        resetGame()
    }

    private fun resetGame() {
        cardStates.forEach { it.value = false }
        matchedCards.clear()
        openedCards.clear()
        score.intValue = 0
        progress.floatValue = 0f
        elapsedTime.intValue = 0
        _images = getShuffledImages().shuffled()
    }

    fun onCardClick(index: Int, images: List<Int>) {
        if (cardStates[index].value || openedCards.size >= 2) return

        cardStates[index].value = true
        openedCards.add(index)

        if (openedCards.size == 2) {
            val firstCard = openedCards[0]
            val secondCard = openedCards[1]

            if (_images[firstCard] == _images[secondCard]) {
                matchedCards.addAll(listOf(firstCard, secondCard))
                incrementScore()
                updateProgress()
                openedCards.clear()
            } else {
                android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
                    cardStates[firstCard].value = false
                    cardStates[secondCard].value = false
                    openedCards.clear()
                }, 1000)
            }
        }
    }

    private fun incrementScore() {
        score.intValue += 10
    }

    private fun updateProgress() {
        progress.floatValue = (score.intValue.toFloat() / (NUM_OF_CARD_STATES / 2))
    }
}
