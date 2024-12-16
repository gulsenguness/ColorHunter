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
import com.gulsenurgunes.myapplication.navigation.FilmScreen
import com.gulsenurgunes.myapplication.navigation.OyunScreen
import com.gulsenurgunes.myapplication.ui.colorfulpuzzle.ColorfulPuzzle
import com.gulsenurgunes.myapplication.ui.home.HomePage
import com.gulsenurgunes.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                AppNavigation()
            }
        }
    }
}


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





