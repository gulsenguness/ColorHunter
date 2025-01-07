package com.gulsenurgunes.myapplication.ui.christmaspassword

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gulsenurgunes.myapplication.R

@Composable
fun PuzzleGameScreen(
    viewModel: ChristmasPasswordViewModel
) {
    val currentQuestion by viewModel.currentQuestion.observeAsState()
    val puzzlePieces by viewModel.puzzleState.observeAsState(emptyList())
    val score by viewModel.score.observeAsState(0)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        currentQuestion?.let { question ->
            QuestionSection(
                question = question.text,
                option = question.option,
                onOptionSelected = { answer -> viewModel.onAnswerSelected(answer) }
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        puzzlePieces?.let { PuzzleSection(puzzlePieces = it) }
        Spacer(modifier = Modifier.height(8.dp))
        puzzlePieces?.let {
            ScoreAndProgressSection(
                score = score,
                progress = it.count { it != null })
        }
    }
}

@Composable
fun QuestionSection(
    question: String,
    option: List<String>,
    onOptionSelected: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = question,
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(8.dp)
        )
        option.forEach { option ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .border(2.dp, Color.Black)
                    .background(Color.Transparent)
                    .clickable { onOptionSelected(option) }
            ) {
                Text(
                    text = option,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black,
                    modifier = Modifier.padding(12.dp)
                )
            }
        }
    }
}

@Composable
fun PuzzleSection(puzzlePieces: List<Int?>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
    ) {
        Grid(
            items = puzzlePieces,
            columns = 3,
            itemContent = { piece ->
                Box(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .background(if (piece != null) Color.White else Color.Gray)
                        .border(1.dp, Color.Black)
                ) {
                    if (piece != null) {
                        Image(
                            painter = painterResource(id = piece),
                            contentDescription = "Puzzle Piece",
                            modifier = Modifier.fillMaxSize()
                        )
                    } else {
                        Image(
                            painter = painterResource(id = R.drawable.tree),
                            contentDescription = "Closed Puzzle Piece",
                            modifier = Modifier.fillMaxSize(),
                        )
                    }
                }
            }
        )
    }
}

@Composable
fun <T> Grid(
    items: List<T>,
    columns: Int,
    itemContent: @Composable (T) -> Unit
) {
    val rows = items.chunked(columns)
    Column {
        rows.forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                row.forEach { item ->
                    Box(modifier = Modifier.weight(1f, fill = true)) {
                        itemContent(item)
                    }
                }
            }
        }
    }
}

@Composable
fun ScoreAndProgressSection(score: Int, progress: Int) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Puan: $score",
            style = MaterialTheme.typography.bodySmall
        )
        LinearProgressIndicator(
            progress = { progress.toFloat() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
        )
    }
}