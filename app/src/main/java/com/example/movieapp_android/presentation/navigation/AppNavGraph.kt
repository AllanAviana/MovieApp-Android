package com.example.movieapp_android.presentation.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.movieapp_android.presentation.screen.detail.DetailScreen
import com.example.movieapp_android.presentation.screen.favorite.FavoriteScreen
import com.example.movieapp_android.presentation.screen.highlight.HighlightScreen
import com.example.movieapp_android.presentation.screen.home.HomeScreen
import com.example.movieapp_android.viewmodel.MovieViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    val movieViewModel: MovieViewModel = viewModel()

    AnimatedNavHost(
        navController = navController,
        startDestination = Routes.HomeScreen.route,
        modifier = modifier,
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { it },
                animationSpec = tween(300)
            )
        },
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { -it },
                animationSpec = tween(300)
            )
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { -it },
                animationSpec = tween(300)
            )
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { it },
                animationSpec = tween(300)
            )
        }
    ) {
        composable(Routes.HomeScreen.route) {
            HomeScreen(navController, viewModel = movieViewModel)
        }
        composable(Routes.DetailScreen.route) {
            DetailScreen(navController, viewModel = movieViewModel)
        }
        composable(Routes.FavoriteScreen.route) {
            FavoriteScreen(viewModel = movieViewModel, navController)
        }
        composable(Routes.HighlightScreen.route) {
            HighlightScreen(viewModel = movieViewModel, navController)
        }
    }
}
