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
import com.gulsenurgunes.myapplication.navigation.colorfulMaze
import com.gulsenurgunes.myapplication.ui.carddetective.CardDetective
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
            composable("film") { colorfulMaze() }
            composable("dizi") { DiziScreen() }
            composable("carddetective") { CardDetective() }
        }
    }
}
