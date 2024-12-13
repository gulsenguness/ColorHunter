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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gulsenurgunes.myapplication.R

@Composable
fun HomePage(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(modifier = Modifier.height(250.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            HomeCard(
                modifier = Modifier.weight(1f),
                icon = painterResource(id = R.drawable.ic_launcher_foreground),
                title = "Anime İzle",
                onClick = {
                    navController.navigate("anime")
                }
            )
            Spacer(modifier = Modifier.width(16.dp))
            HomeCard(
                modifier = Modifier
                    .weight(1f),
                icon = painterResource(id = R.drawable.ic_launcher_foreground),
                title = "Film İzle",
                onClick = {
                    navController.navigate("film")
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
                icon = painterResource(id = R.drawable.ic_launcher_foreground),
                title = "Dizi İzle",
                onClick = {
                    navController.navigate("dizi")
                }
            )
            Spacer(modifier = Modifier.width(16.dp))
            HomeCard(
                modifier = Modifier
                    .weight(1f),
                icon = painterResource(id = R.drawable.ic_launcher_foreground),
                title = "Oyun İzle",
                onClick = {
                    navController.navigate("oyun")
                }
            )
        }
    }

}
