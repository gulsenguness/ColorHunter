package com.gulsenurgunes.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gulsenurgunes.myapplication.ui.colorfulmaze.colorfulMaze
import com.gulsenurgunes.myapplication.ui.colorfulmaze.generateMaze
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
            val maze = generateMaze(5, 5)

            colorfulMaze(
                maze = maze,
                onCellClick = { cell->
                    println("Hücre tıklandı: ${cell.row}, ${cell.col}")
                }
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
