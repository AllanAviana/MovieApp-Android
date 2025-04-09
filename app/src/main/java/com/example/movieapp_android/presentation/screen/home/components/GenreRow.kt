package com.example.movieapp_android.presentation.screen.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp_android.data.model.Movie
import com.example.movieapp_android.ui.theme.jostFontFamily
import com.example.movieapp_android.viewmodel.MovieViewModel

@Composable
fun GenreRow(
    genre: String,
    movies: List<Movie>,
    navigation: () -> Unit,
    viewModel: MovieViewModel
) {
    Text(
        text = genre,
        color = Color.White.copy(alpha = 0.8f),
        fontSize = 20.sp,
        modifier = Modifier.padding(vertical = 8.dp),
        fontFamily = jostFontFamily
    )

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .padding(end = 16.dp, bottom = 8.dp)
    ) {
        items(movies) { movie ->
            MovieItem(movie = movie, navigation = navigation, viewModel = viewModel)
        }
    }
}