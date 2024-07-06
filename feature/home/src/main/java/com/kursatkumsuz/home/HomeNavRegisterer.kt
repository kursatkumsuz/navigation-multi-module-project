package com.kursatkumsuz.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.navigation.Screen.DetailScreen
import com.example.navigation.Screen.HomeScreen
import com.example.navigation.NavRegisterer
import com.example.navigation.navigateTo

class HomeNavRegisterer : NavRegisterer {
    override fun registerGraph(navGraphBuilder: NavGraphBuilder, navController: NavController) {
        navGraphBuilder.composable<HomeScreen> {
            HomeScreen(onNavigateDetailScreen = {navController.navigateTo(screen = DetailScreen(message = "Detail Screen Message"))})
        }
    }
}