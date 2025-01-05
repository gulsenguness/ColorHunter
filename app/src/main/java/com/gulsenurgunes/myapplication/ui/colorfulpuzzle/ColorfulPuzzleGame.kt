package com.gulsenurgunes.myapplication.ui.colorfulpuzzle

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import java.lang.reflect.Modifier

@Composable
fun ScoreAndProgreddDection(score:Int,progress:Float){
    Column(
        modifier = androidx.compose.ui.Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Puan: $score",
            style = MaterialTheme.typography.bodySmall
        )
        LinearProgressIndicator(
            progress = { progress.coerceIn(0f, 1f) },
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
        )
    }
}

fun handleCardClick(
    index: Int,
    cardStates: List<MutableState<Boolean>>,
    openedCards: MutableList<Int>,
    images: List<Int>,
    onCorrectMatch: () -> Unit,
    onProgress: () -> Unit
) {
    if (cardStates[index].value || openedCards.size >= 2) return
    cardStates[index].value = true
    openedCards.add(index)

    if (openedCards.size == 2) {
        val firstCard = openedCards[0]
        val secondCard = openedCards[1]
        if (images[firstCard] == images[secondCard]) {
            onCorrectMatch
        }
        onProgress
        if (images[firstCard] != images[secondCard]) {
            android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
                cardStates[firstCard].value = false
                cardStates[secondCard].value = false
            }, 1000)
        }
        openedCards.clear()
    }
}