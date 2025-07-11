package com.example.movieapp_android.presentation.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.movieapp_android.presentation.screen.detail.components.DetailImageHeader
import com.example.movieapp_android.presentation.screen.detail.components.DetailInfoSection
import com.example.movieapp_android.presentation.screen.detail.components.FavoriteButtonSection
import com.example.movieapp_android.viewmodel.MovieViewModel

@Composable
fun DetailScreen(navController: NavHostController, viewModel: MovieViewModel) {
    val detailUiState = viewModel.detailUiState.collectAsState()
    val favorites = viewModel.favorites.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().background(Color.Black).padding(top = 16.dp)
    ) {
        DetailImageHeader(detailUiState.value.movie?.poster_path, detailUiState.value.movie?.title ?: "", navController)
        DetailInfoSection(detailUiState, viewModel)
        FavoriteButtonSection(viewModel, detailUiState.value.movie, favorites.value)
    }
}
