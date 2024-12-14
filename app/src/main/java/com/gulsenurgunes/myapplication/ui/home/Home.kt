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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gulsenurgunes.myapplication.DiziScreen
import com.gulsenurgunes.myapplication.FilmScreen
import com.gulsenurgunes.myapplication.OyunScreen
import com.gulsenurgunes.myapplication.R
import com.gulsenurgunes.myapplication.ui.colorfulpuzzle.ColorfulPuzzle
import com.gulsenurgunes.myapplication.ui.theme.MyApplicationTheme

@Composable
fun HomePage(
    navController: NavController
) {
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
                animationId = R.raw.bell,
                title = "Colorful Puzzle",
                onClick = {
                    navController.navigate("colorfulpuzzle")
                }
            )
            Spacer(modifier = Modifier.width(16.dp))
            HomeCard(
                modifier = Modifier
                    .weight(1f),
                animationId = R.raw.bell,
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
                animationId = R.raw.bell,
                title = "Dizi İzle",
                onClick = {
                    navController.navigate("dizi")
                }
            )
            Spacer(modifier = Modifier.width(16.dp))
            HomeCard(
                modifier = Modifier
                    .weight(1f),
                animationId = R.raw.bell,
                title = "Oyun İzle",
                onClick = {
                    navController.navigate("oyun")
                }
            )
        }
    }

}

//Sil bunu
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "home") {
            composable("home") { HomePage(navController) }
            composable("colorfulpuzzle") { ColorfulPuzzle() }
            composable("film") { FilmScreen() }
            composable("dizi") { DiziScreen() }
            composable("oyun") { OyunScreen() }
        }
    }
}
