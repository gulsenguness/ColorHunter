package com.gulsenurgunes.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gulsenurgunes.myapplication.data.AppDatabase
import com.gulsenurgunes.myapplication.ui.christmaspassword.ChristmasPasswordViewModel
import com.gulsenurgunes.myapplication.ui.christmaspassword.ChristmasPasswordViewModelFactory
import com.gulsenurgunes.myapplication.ui.carddetective.CardDetective
import com.gulsenurgunes.myapplication.ui.carddetective.DetectiveViewModel
import com.gulsenurgunes.myapplication.ui.christmaspassword.ChristmasPassword
import com.gulsenurgunes.myapplication.ui.colorfulpuzzle.colorfulPuzzle
import com.gulsenurgunes.myapplication.ui.home.homePage


@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val questionDao = AppDatabase.getDatabase(context).questionDao()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") { homePage(navController = navController) }
        composable("colorfulpuzzle") { colorfulPuzzle() }
        composable("colorfulmaze") { colorfulMaze() }
        composable("christmaspassword") {
            val christmasPasswordViewModel: ChristmasPasswordViewModel = viewModel(
                factory = ChristmasPasswordViewModelFactory(questionDao)
            )
            ChristmasPassword(viewModel = christmasPasswordViewModel)
        }
        composable("carddetective") {
            val detectiveViewModel: DetectiveViewModel = viewModel()
            CardDetective(viewModel = detectiveViewModel)
        }
    }
}



@Composable
fun colorfulMaze() {}
