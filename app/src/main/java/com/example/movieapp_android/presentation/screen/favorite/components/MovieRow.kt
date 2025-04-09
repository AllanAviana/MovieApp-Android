package com.example.movieapp_android.presentation.screen.favorite.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.movieapp_android.data.model.Movie
import com.example.movieapp_android.viewmodel.MovieViewModel

@Composable
fun MovieRow(rowMovies: List<Movie>, navController: NavHostController, viewModel: MovieViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = if (rowMovies.size == 1) Arrangement.Start else Arrangement.SpaceBetween
    ) {
        rowMovies.forEach { movie ->
            MovieItem(movie, navController, viewModel)
        }
    }
}