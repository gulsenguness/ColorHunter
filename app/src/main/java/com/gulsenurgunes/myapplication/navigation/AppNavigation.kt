package com.gulsenurgunes.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gulsenurgunes.myapplication.ui.colorfulmaze.Cell
import com.gulsenurgunes.myapplication.ui.colorfulmaze.colorfulMaze
import com.gulsenurgunes.myapplication.ui.colorfulpuzzle.colorfulPuzzle
import com.gulsenurgunes.myapplication.ui.home.homePage

@Suppress("FunctionNaming")
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") { homePage(navController = navController) }
        composable("colorfulpuzzle") { colorfulPuzzle() }
        composable("colorfulmaze") {
            colorfulMaze(
                maze = listOf(
                    listOf(Cell(0, 0, false), Cell(0, 1, true), Cell(0, 2, false)),
                    listOf(Cell(1, 0, false), Cell(1, 1, false), Cell(1, 2, false)),
                    listOf(Cell(2, 0, false), Cell(2, 1, true), Cell(2, 2, false))
                )
            )
        }
        composable("dizi") { DiziScreen() }
        composable("oyun") { OyunScreen() }
    }
}

@Composable
fun DiziScreen() {}

@Composable
fun OyunScreen() {}