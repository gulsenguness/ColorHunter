package com.gulsenurgunes.myapplication.ui.colorfulpuzzle

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ColorfulPuzzle(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ColorfulPuzzleCard(
                modifier = Modifier.weight(1f),
                title = "",
                onClick = {
                }
            )
            Spacer(modifier = Modifier.width(16.dp))
            ColorfulPuzzleCard(
                modifier = Modifier
                    .weight(1f),
                title = "",
                onClick = {
                }
            )
            Spacer(modifier = Modifier.width(16.dp))
            ColorfulPuzzleCard(
                modifier = Modifier
                    .weight(1f),
                title = "",
                onClick = {
                }
            )
            Spacer(modifier = Modifier.width(16.dp))
            ColorfulPuzzleCard(
                modifier = Modifier
                    .weight(1f),
                title = "",
                onClick = {
                }
            )
        }
    }
}

@Composable
fun ColorfulPuzzleCard(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit
){
    Card(
        modifier = modifier
            .height(150.dp)
            .clickable { onClick() },
        shape = MaterialTheme.shapes.medium,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ColorfulPuzzle()
}