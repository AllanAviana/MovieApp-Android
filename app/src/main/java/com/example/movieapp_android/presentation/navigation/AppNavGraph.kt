package com.example.movieapp_android.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movieapp_android.presentation.screen.detail.DetailScreen
import com.example.movieapp_android.presentation.screen.favorite.FavoriteScreen
import com.example.movieapp_android.presentation.screen.highlight.HighlightScreen
import com.example.movieapp_android.presentation.screen.home.HomeScreen
import com.example.movieapp_android.viewmodel.MovieViewModel

@Composable
fun AppNavGraph(navController: NavHostController = rememberNavController(), modifier: Modifier){
    val movieViewModel: MovieViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Routes.HomeScreen.route,
        modifier = modifier
    ){

        composable(route = "home_screen"){
            HomeScreen(navController, viewModel = movieViewModel)
        }

        composable(route = "detail_screen"){
            DetailScreen(navController, viewModel =  movieViewModel)
        }
        composable(route = "favorites"){
            FavoriteScreen(viewModel = movieViewModel)
        }
        composable(route = "highlights"){
            HighlightScreen(viewModel = movieViewModel)

        }
    }
}