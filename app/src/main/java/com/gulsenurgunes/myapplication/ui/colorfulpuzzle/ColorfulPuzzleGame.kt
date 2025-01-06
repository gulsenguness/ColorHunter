package com.gulsenurgunes.myapplication.ui.colorfulpuzzle

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

@Composable
fun ScoreAndProgreddDection(
    score: Int,
    progress: Float,
    elepsedTime: Int
) {
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
