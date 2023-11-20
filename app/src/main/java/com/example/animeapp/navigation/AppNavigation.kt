package com.example.animeapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.animeapp.ui.theme.screen.MyTopBar
import com.example.animeapp.ui.theme.screen.DetailScreen
import com.example.animeapp.ui.theme.screen.ProfileScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController, startDestination = "main") {
        composable("main") {
            MyTopBar(navController)
        }
        composable("profile") {
            ProfileScreen(navController = navController)
        }
        composable(
            route = "detail/{animeId}",
            arguments = listOf(navArgument("animeId") { type = NavType.StringType })
        ) { backStackEntry ->
            val animeId = backStackEntry.arguments?.getString("animeId")
            DetailScreen(navController = navController, animeId = animeId ?: "")

        }
    }
}