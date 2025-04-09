package com.example.movieapp_android.presentation.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.movieapp_android.R
import com.example.movieapp_android.data.model.DetailUiState
import com.example.movieapp_android.data.model.Movie
import com.example.movieapp_android.presentation.screen.detail.components.DetailImageHeader
import com.example.movieapp_android.presentation.screen.detail.components.DetailInfoSection
import com.example.movieapp_android.presentation.screen.detail.components.FavoriteButtonSection
import com.example.movieapp_android.ui.theme.jostFontFamily
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
