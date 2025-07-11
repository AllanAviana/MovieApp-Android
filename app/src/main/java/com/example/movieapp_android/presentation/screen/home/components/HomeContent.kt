package com.example.movieapp_android.presentation.screen.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.movieapp_android.presentation.uistate.HomeUiState
import com.example.movieapp_android.viewmodel.MovieViewModel

@Composable
fun HomeContent(navController: NavHostController, viewModel: MovieViewModel, uiState: HomeUiState) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(start = 16.dp, top = 30.dp)
    ) {
        item {
            AppLogo()
            RandomMovieBanner(uiState)
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            GenreRow("Romance", uiState.romance, { navController.navigate("detail_screen") }, viewModel)
            GenreRow("Horror", uiState.horror, { navController.navigate("detail_screen") }, viewModel)
            GenreRow("Action", uiState.action, { navController.navigate("detail_screen") }, viewModel)
            GenreRow("Thriller", uiState.suspense, { navController.navigate("detail_screen") }, viewModel)
        }
    }
}