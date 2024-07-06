package com.example.navigation

import androidx.navigation.NavController


fun NavController.navigateTo(screen: Screen) {
    navigate(screen) {
        popUpTo(0)
    }
}

