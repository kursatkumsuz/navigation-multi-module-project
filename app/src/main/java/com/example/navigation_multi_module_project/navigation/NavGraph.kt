package com.example.navigation_multi_module_project.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.navigation.HomeScreen
import com.example.navigation.NavProvider

@Composable
fun NavGraph(navController: NavHostController, navProvider: NavProvider) {
    NavHost(navController = navController, startDestination = HomeScreen) {
        navProvider.homeScreen.registerGraph(navGraphBuilder = this, navController = navController)
        navProvider.detailScreen.registerGraph(navGraphBuilder = this, navController = navController)
    }
}