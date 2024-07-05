package com.example.navigation

sealed class Screens(val route : String) {
    data object HomeScreen : Screens("home_screen")
    data object DetailScreen : Screens("detail_screen")
}