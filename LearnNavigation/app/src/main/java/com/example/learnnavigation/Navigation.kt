package com.example.learnnavigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MyNavigationApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.ScreenA) {
        composable(Routes.ScreenA) {
            ScreenA(navController)
        }
        composable(route = Routes.ScreenB + "/{name}") { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: "No Name"
            ScreenB(name)
        }
    }
}
