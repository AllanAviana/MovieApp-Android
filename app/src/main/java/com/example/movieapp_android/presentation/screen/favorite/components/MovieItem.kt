package com.example.movieapp_android.presentation.screen.favorite.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.movieapp_android.data.model.Movie
import com.example.movieapp_android.ui.theme.jostFontFamily
import com.example.movieapp_android.viewmodel.MovieViewModel

@Composable
fun MovieItem(movie: Movie, navController: NavHostController, viewModel: MovieViewModel) {
    Column(
        modifier = Modifier.padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                model = "https://image.tmdb.org/t/p/w500/${movie.poster_path}"
            ),
            contentDescription = movie.title,
            modifier = Modifier
                .width(160.dp)
                .height(260.dp)
                .clickable {
                    viewModel.updateDetailUiState(movie)
                    navController.navigate("detail_screen")
                },
            contentScale = ContentScale.Crop
        )
        Text(
            text = movie.title,
            color = Color.White,
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 4.dp).width(160.dp),
            maxLines = 1,
            fontFamily = jostFontFamily
        )
    }
}