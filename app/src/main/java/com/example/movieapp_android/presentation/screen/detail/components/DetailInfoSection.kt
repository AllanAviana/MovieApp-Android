package com.example.movieapp_android.presentation.screen.detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp_android.data.model.DetailUiState
import com.example.movieapp_android.ui.theme.jostFontFamily
import com.example.movieapp_android.viewmodel.MovieViewModel

@Composable
fun DetailInfoSection(detailUiState: State<DetailUiState>, viewModel: MovieViewModel) {
    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${detailUiState.value.movie?.vote_average ?: "N/A"}/10",
                fontSize = 14.sp,
                color = Color.White,
                fontFamily = jostFontFamily
            )
            Text(
                text = "${detailUiState.value.movie?.vote_count} votes",
                fontSize = 14.sp,
                color = Color.White,
                fontFamily = jostFontFamily
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val vote = detailUiState.value.movie?.vote_average ?: 0.0
            val starCount = (vote / 2).toInt()

            Row(
                modifier = Modifier.fillMaxWidth(0.5f),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                repeat(starCount) {
                    Image(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(Color.Yellow)
                    )
                }
            }

            Text(
                text = detailUiState.value.movie?.release_date ?: "N/A",
                fontSize = 14.sp,
                color = Color.White,
                fontFamily = jostFontFamily
            )
        }

        Text(
            text = viewModel.genresMovie(detailUiState.value.movie?.genre_ids ?: emptyList()),
            fontSize = 14.sp,
            color = Color.White.copy(alpha = 0.5f),
            modifier = Modifier.padding(top = 6.dp),
            fontFamily = jostFontFamily
        )

        Text(
            text = detailUiState.value.movie?.overview ?: "N/A",
            fontSize = 18.sp,
            color = Color.White,
            modifier = Modifier.padding(top = 30.dp),
            fontFamily = jostFontFamily
        )
    }
}