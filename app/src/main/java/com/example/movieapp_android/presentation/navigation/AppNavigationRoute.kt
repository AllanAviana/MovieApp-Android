package com.example.movieapp_android.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Routes(val route: String, val icon: ImageVector, val label: String) {
    object HomeScreen : Routes("home_screen", icon = Icons.Filled.Home, label = "Home")
    object DetailScreen : Routes("detail_screen", icon = Icons.Filled.Home, label = "Detail")
    object FavoriteScreen : Routes("favorites", icon = Icons.Default.Favorite, label = "Favorites")
    object HighlightScreen : Routes("highlights", icon = Icons.Default.Star, label = "Highlights")
}