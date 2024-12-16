package com.gulsenurgunes.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gulsenurgunes.myapplication.ui.colorfulpuzzle.ColorfulPuzzle
import com.gulsenurgunes.myapplication.ui.home.HomePage

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") { HomePage(navController = navController) }
        composable("colorfulpuzzle") { ColorfulPuzzle() }
        composable("film") { FilmScreen() }
        composable("dizi") { DiziScreen() }
        composable("oyun") { OyunScreen() }
    }
}

@Composable
fun FilmScreen(modifier: Modifier = Modifier) {

}

@Composable
fun DiziScreen(modifier: Modifier = Modifier) {

}

@Composable
fun OyunScreen(modifier: Modifier = Modifier) {

}