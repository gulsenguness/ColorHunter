package com.gulsenurgunes.myapplication.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gulsenurgunes.myapplication.R
import com.gulsenurgunes.myapplication.ui.components.LottieSnow
import com.gulsenurgunes.myapplication.ui.theme.NYTheme
import com.gulsenurgunes.myapplication.ui.theme.NYTheme.padding

@Composable
fun homePage(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(NYTheme.padding.dimension16),
        verticalArrangement = Arrangement.spacedBy(padding.dimension16)
    ) {
        LottieSnow(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(padding.dimension16)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            HomeCard(
                modifier = Modifier.weight(1f),
                animationId = R.raw.bell,
                title = "Colorful Puzzle",
                onClick = {
                    navController.navigate("colorfulpuzzle")
                }
            )
            Spacer(modifier = Modifier.width(padding.dimension16))
            HomeCard(
                modifier = Modifier
                    .weight(1f),
                animationId = R.raw.bell,
                title = "Colorful Maze",
                onClick = {
                    navController.navigate("colorfulmaze")
                }
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            HomeCard(
                modifier = Modifier
                    .weight(1f),
                animationId = R.raw.bell,
                title = "Christmas Password",
                onClick = {
                    navController.navigate("christmaspassword")
                }
            )
            Spacer(modifier = Modifier.width(padding.dimension16))
            HomeCard(
                modifier = Modifier
                    .weight(1f),
                animationId = R.raw.bell,
                title = "Card Detective",
                onClick = {
                    navController.navigate("carddetective")
                }
            )
        }
        Spacer(modifier = Modifier.height(padding.dimension150))
    }
}
