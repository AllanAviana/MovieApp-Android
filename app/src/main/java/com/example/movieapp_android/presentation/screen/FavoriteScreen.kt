package com.example.movieapp_android.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.movieapp_android.ui.theme.jostFontFamily
import com.example.movieapp_android.viewmodel.MovieViewModel

@Composable
fun FavoriteScreen(viewModel: MovieViewModel) {
    val favorites = viewModel.favorites.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(top = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                text = "Favorites\nMovies",
                color = Color.White,
                fontSize = 46.sp,
                fontFamily = jostFontFamily,
                lineHeight = 50.sp,
                textAlign = TextAlign.Center
            )
        }

        items(favorites.value.chunked(2)) { rowMovies ->
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = if (rowMovies.size == 1) Arrangement.Start else Arrangement.SpaceBetween
            ) {
                rowMovies.forEach { movie ->
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
                                .height(260.dp),
                            contentScale = ContentScale.Crop
                        )
                        Text(
                            text = movie.title,
                            color = Color.White,
                            fontSize = 14.sp,
                            modifier = Modifier.padding(top = 4.dp),
                            maxLines = 1,
                            fontFamily = jostFontFamily
                        )
                    }
                }
            }
        }
    }
}
