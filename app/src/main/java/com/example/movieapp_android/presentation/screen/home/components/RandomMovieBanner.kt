package com.example.movieapp_android.presentation.screen.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.movieapp_android.presentation.uistate.HomeUiState

@Composable
fun RandomMovieBanner(uiState: HomeUiState) {
    Box {
        Image(
            painter = rememberAsyncImagePainter(model = "https://image.tmdb.org/t/p/w500/${uiState.randomMovie?.poster_path}"),
            contentDescription = "Home Image",
            modifier = Modifier
                .height(220.dp)
                .fillMaxWidth()
                .padding(end = 16.dp),
            contentScale = ContentScale.FillBounds
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(35.dp)
                .align(Alignment.BottomCenter)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.7f)
                        )
                    )
                )
        )
    }

    Text(
        text = uiState.randomMovie?.title ?: "N/A",
        color = Color.White.copy(alpha = 0.8f),
        fontSize = 14.sp,
        modifier = Modifier.padding(top = 8.dp)
    )
}