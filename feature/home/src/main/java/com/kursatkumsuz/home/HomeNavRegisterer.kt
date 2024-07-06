package com.kursatkumsuz.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.navigation.DetailScreen
import com.example.navigation.HomeScreen
import com.example.navigation.NavRegisterer

class HomeNavRegisterer : NavRegisterer {
    override fun registerGraph(navGraphBuilder: NavGraphBuilder, navController: NavController) {
        navGraphBuilder.composable<HomeScreen> {
            HomeScreen(onNavigateDetailScreen = {navController.navigate(route = DetailScreen(message = "Detail Screen Message"))})
        }
    }
}