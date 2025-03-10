package com.gulsenurgunes.myapplication.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.gulsenurgunes.myapplication.ui.theme.NYTheme.padding

@Composable
fun HomeCard(
    modifier: Modifier = Modifier,
    animationId: Int,
    title: String,
    onClick: () -> Unit,
    cardColor: Color = Color.Transparent
) {
    Card(
        modifier = modifier
            .height(padding.dimension150)
            .clickable { onClick() }
            .background(cardColor)
            .shadow(elevation = 8.dp, shape = MaterialTheme.shapes.medium),
        shape = MaterialTheme.shapes.medium,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding.dimension8),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnimationItem(animationId)
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}
