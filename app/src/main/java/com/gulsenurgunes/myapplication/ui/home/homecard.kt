package com.gulsenurgunes.myapplication.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.clickable
import androidx.compose.ui.graphics.Color
import com.gulsenurgunes.myapplication.ui.theme.NYTheme.padding


@Composable
fun homeCard(
    modifier: Modifier = Modifier,
    animationId: Int,
    title: String,
    onClick: () -> Unit,
    cardColor: Color = Color.Red
) {
    Card(
        modifier = modifier
            .height(padding.dimension150)
            .clickable { onClick() }
            .background(cardColor),
        shape = MaterialTheme.shapes.medium,

        ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding.dimension8),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            animationItem(animationId)
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}
