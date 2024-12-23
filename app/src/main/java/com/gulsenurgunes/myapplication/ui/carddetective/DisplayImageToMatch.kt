package com.gulsenurgunes.myapplication.ui.carddetective

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun DisplayImageToMatch(imageToMatch: Int?) {
    imageToMatch?.let {
        Card(
            modifier = Modifier
                .size(100.dp)
                .background(Color.LightGray),
            onClick = {}
        ) {
            Image(painterResource(id = it), contentDescription = null)
        }
    }
}