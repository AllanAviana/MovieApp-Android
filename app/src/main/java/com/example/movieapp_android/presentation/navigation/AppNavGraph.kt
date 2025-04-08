package com.example.movieapp_android.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movieapp_android.presentation.screen.DetailScreen
import com.example.movieapp_android.presentation.screen.HomeScreen
import com.example.movieapp_android.viewmodel.MovieViewModel

@Composable
fun AppNavGraph(navController: NavHostController = rememberNavController()){
    val movieViewModel: MovieViewModel = viewModel()

    NavHost(navController = navController, startDestination = Routes.HomeScreen.route){

        composable(route = "home_screen"){
            HomeScreen(navController, viewModel = movieViewModel)
        }

        composable(route = "detail_screen"){
            DetailScreen(navController, viewModel =  movieViewModel)
        }
    }
}