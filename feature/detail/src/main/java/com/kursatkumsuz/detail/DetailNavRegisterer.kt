package com.kursatkumsuz.detail

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.navigation.Screen.DetailScreen
import com.example.navigation.Screen.HomeScreen
import com.example.navigation.NavRegisterer
import com.example.navigation.navigateTo

class DetailNavRegisterer : NavRegisterer {
    override fun registerGraph(navGraphBuilder: NavGraphBuilder, navController: NavController) {
        navGraphBuilder.composable<DetailScreen> {
           val args = it.toRoute<DetailScreen>()
            args.message?.let { msg -> DetailScreen(message = msg,onNavigateHomeScreen = {navController.navigateTo(HomeScreen)}) }
        }
    }
}

