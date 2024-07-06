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
            // toRoute() fonksiyonu sayesinde navigation ile gönderilen veriyi alınır
            val args = it.toRoute<DetailScreen>()
            DetailScreen(onNavigateHomeScreen = { navController.navigateTo(HomeScreen) })
        }
    }
}
}

