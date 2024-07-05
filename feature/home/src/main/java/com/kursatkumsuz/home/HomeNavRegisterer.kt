package com.kursatkumsuz.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.navigation.NavRegisterer
import com.example.navigation.Screens

class HomeNavRegisterer : NavRegisterer {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController
    ) {
        navGraphBuilder.composable(Screens.HomeScreen.route) {
            HomeScreen(onNavigateHomeScreen = {navController.navigate(Screens.HomeScreen.route)})
        }
    }
}