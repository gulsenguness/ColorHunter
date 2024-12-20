package com.gulsenurgunes.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gulsenurgunes.myapplication.navigation.AppNavigation
import com.gulsenurgunes.myapplication.navigation.DiziScreen
import com.gulsenurgunes.myapplication.navigation.OyunScreen
import com.gulsenurgunes.myapplication.ui.colorfulmaze.Cell
import com.gulsenurgunes.myapplication.ui.colorfulmaze.colorfulMaze
import com.gulsenurgunes.myapplication.ui.colorfulpuzzle.colorfulPuzzle
import com.gulsenurgunes.myapplication.ui.home.homePage
import com.gulsenurgunes.myapplication.ui.theme.nyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            nyTheme {
                AppNavigation()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    nyTheme {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "home") {
            composable("home") { homePage(navController) }
            composable("colorfulpuzzle") { colorfulPuzzle() }
            composable("film") {
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
}





