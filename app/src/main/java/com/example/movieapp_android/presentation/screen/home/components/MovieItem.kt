package com.example.movieapp_android.presentation.screen.home.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.movieapp_android.data.model.Movie
import com.example.movieapp_android.ui.theme.jostFontFamily
import com.example.movieapp_android.viewmodel.MovieViewModel

@Composable
fun MovieItem(movie: Movie, navigation: () -> Unit, viewModel: MovieViewModel) {
    val visible = remember { mutableStateOf(false) }

    val alpha by animateFloatAsState(
        targetValue = if (visible.value) 1f else 0f,
        animationSpec = tween(durationMillis = 2000),
        label = ""
    )

    val scale by animateFloatAsState(
        targetValue = if (visible.value) 1f else 0.9f,
        animationSpec = tween(durationMillis = 2000),
        label = ""
    )

    LaunchedEffect(Unit) {
        visible.value = true
    }

    Column {
        Image(
            painter = rememberAsyncImagePainter(model = "https://image.tmdb.org/t/p/w500/${movie.poster_path}"),
            contentDescription = "",
            modifier = Modifier
                .graphicsLayer {
                    this.alpha = alpha
                    this.scaleX = scale
                    this.scaleY = scale
                }
                .width(136.dp)
                .height(202.dp)
                .clickable {
                    navigation()
                    viewModel.updateDetailUiState(movie)
                },
            contentScale = ContentScale.Crop
        )

        Text(
            text = movie.title,
            color = Color.White.copy(alpha = 0.8f),
            fontSize = 14.sp,
            modifier = Modifier
                .padding(top = 8.dp)
                .width(120.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontFamily = jostFontFamily
        )
    }
}