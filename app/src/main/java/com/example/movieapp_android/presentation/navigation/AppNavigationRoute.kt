package com.example.movieapp_android.presentation.navigation

sealed class Routes(val route: String) {
    object HomeScreen : Routes("home_screen")
    object DetailScreen : Routes("detail_screen")
}