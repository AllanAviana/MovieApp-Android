package com.example.movieapp_android.presentation.screen.home

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.movieapp_android.R
import com.example.movieapp_android.data.model.Movie
import com.example.movieapp_android.presentation.screen.home.components.HomeContent
import com.example.movieapp_android.presentation.screen.home.components.LoadingScreen
import com.example.movieapp_android.ui.theme.jostFontFamily
import com.example.movieapp_android.viewmodel.MovieViewModel
import com.spr.jetpack_loading.components.indicators.PulsatingDot

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HomeScreen(navController: NavHostController, viewModel: MovieViewModel) {
    val homeUiState = viewModel.genres.collectAsState()

    if (homeUiState.value.isSuccess) {
        HomeContent(navController, viewModel, homeUiState.value)
    } else {
        LoadingScreen()
    }
}


