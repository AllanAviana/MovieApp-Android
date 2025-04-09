package com.example.movieapp_android.presentation.screen.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.movieapp_android.viewmodel.MovieViewModel
import com.example.movieapp_android.presentation.screen.favorite.components.FavoritesHeader
import com.example.movieapp_android.presentation.screen.favorite.components.MovieRow

@Composable
fun FavoriteScreen(viewModel: MovieViewModel, navController: NavHostController) {
    val favorites = viewModel.favorites.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(top = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            FavoritesHeader()
        }

        items(favorites.value.chunked(2)) { rowMovies ->
            MovieRow(rowMovies, navController, viewModel)
        }
    }
}