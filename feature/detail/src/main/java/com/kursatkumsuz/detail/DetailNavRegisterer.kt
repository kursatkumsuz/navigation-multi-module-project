package com.kursatkumsuz.detail

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.navigation.DetailScreen
import com.example.navigation.HomeScreen
import com.example.navigation.NavRegisterer

class DetailNavRegisterer : NavRegisterer {
    override fun registerGraph(navGraphBuilder: NavGraphBuilder, navController: NavController) {
        navGraphBuilder.composable<DetailScreen> {
           val args = it.toRoute<DetailScreen>()
            args.message?.let { msg -> DetailScreen(message = msg,onNavigateHomeScreen = {navController.navigate(HomeScreen)}) }
        }
    }
}

