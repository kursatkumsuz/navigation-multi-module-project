package com.kursatkumsuz.detail

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.navigation.NavRegisterer
import com.example.navigation.Screens

class DetailNavRegisterer : NavRegisterer {
    override fun registerGraph(navGraphBuilder: NavGraphBuilder, navController: NavController) {
        navGraphBuilder.composable(route = Screens.DetailScreen.route) {
            DetailScreen(onNavigateHomeScreen = {navController.navigate(Screens.DetailScreen.route)})
        }
    }
}