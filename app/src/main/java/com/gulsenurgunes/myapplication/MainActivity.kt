package com.gulsenurgunes.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gulsenurgunes.myapplication.navigation.AppNavigation
import com.gulsenurgunes.myapplication.ui.carddetective.CardDetective
import com.gulsenurgunes.myapplication.ui.carddetective.DetectiveViewModel
import com.gulsenurgunes.myapplication.ui.christmaspassword.ChristmasPassword
import com.gulsenurgunes.myapplication.ui.christmaspassword.ChristmasPasswordViewModel
import com.gulsenurgunes.myapplication.ui.colorfulmaze.ColorfulMaze
import com.gulsenurgunes.myapplication.ui.colorfulpuzzle.ColorfulPuzzle
import com.gulsenurgunes.myapplication.ui.home.HomePage
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

// @Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    nyTheme {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "home") {
            composable("home") { HomePage(navController) }
            composable("colorfulpuzzle") { ColorfulPuzzle() }
            composable("film") { ColorfulMaze() }
            composable("christmaspassword") {
                val christmasPasswordViewModel: ChristmasPasswordViewModel = viewModel()
                ChristmasPassword(viewModel = christmasPasswordViewModel)
            }
            composable("carddetective") {
                val detectiveViewModel: DetectiveViewModel = viewModel()
                CardDetective(viewModel = detectiveViewModel)
            }
        }
    }
}
