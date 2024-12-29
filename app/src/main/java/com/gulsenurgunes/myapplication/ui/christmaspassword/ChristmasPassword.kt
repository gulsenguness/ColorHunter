package com.gulsenurgunes.myapplication.ui.christmaspassword

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gulsenurgunes.myapplication.R
import com.gulsenurgunes.myapplication.ui.components.LottieSnow

@Composable
fun ChristmasPassword(){
    val puzzlePieces = listOf<Int?>(
        R.drawable.noelhediye,
        R.drawable.noel1,
        R.drawable.noel2,
        R.drawable.noel3,
        R.drawable.noel4,
        R.drawable.noel7,
        R.drawable.noel8,
        R.drawable.noel9,
        R.drawable.tree,
    )
    Column(
        modifier = Modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieSnow(
            modifier = Modifier
                .size(150.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        PuzzleGameScreen(
            question = "Noel Baba'nın kızağını hangi hayvan çeker?",
            option = listOf("Ren geyiği", "Kurt", "Kedi"),
            onOptionSelected = { answer -> println("Seçilen cevap: \$answer") } ,
            puzzlePieces = puzzlePieces,
            score = 50,
            progress = 0.5f
        )
    }
}

