package com.example.movieapp_android.presentation.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.movieapp_android.ui.theme.jostFontFamily
import com.example.movieapp_android.viewmodel.MovieViewModel

@Composable
fun DetailScreen(navController: NavHostController, viewModel: MovieViewModel) {
    val detailUiState = viewModel.detailUiState.collectAsState()
    val favorites = viewModel.favorites.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f)
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = "https://image.tmdb.org/t/p/w500/${detailUiState.value.movie?.poster_path}"),
                contentDescription = "Detail Image",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Fit
            )

            Text(
                text = detailUiState.value.movie?.title ?: "",
                color = Color.White.copy(alpha = 0.8f),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .padding(start = 16.dp)
                    .zIndex(1f),
                fontSize = 16.sp,
                fontFamily = jostFontFamily
            )

            IconButton(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(horizontal = 8.dp, vertical = 16.dp)
                    .size(41.dp),
                colors = IconButtonColors(
                    containerColor = Color.White,
                    contentColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    disabledContentColor = Color.White.copy(alpha = 0.3f)
                )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.chevronleft),
                    contentDescription = "Back button",
                    modifier = Modifier
                        .size(24.dp)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.2f)
                    .align(Alignment.BottomStart)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.7f)
                            )
                        )
                    )
            )
        }

        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.5f),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    val vote = detailUiState.value.movie?.vote_average ?: 0.0
                    val starCount = (vote / 2).toInt()

                    for (i in 0 until starCount) {
                        Image(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(Color.Yellow)
                        )
                    }
                }

                Text(
                    text = "${detailUiState.value.movie?.release_date ?: "N/A"}",
                    fontSize = 14.sp,
                    color = Color.White,
                    fontFamily = jostFontFamily
                )
            }

            Text(
                text = "${viewModel.genresMovie(detailUiState.value.movie?.genre_ids!!)}",
                fontSize = 14.sp,
                color = Color.White.copy(alpha = 0.5f),
                modifier = Modifier
                    .padding(top = 6.dp),
                fontFamily = jostFontFamily
            )

            Text(
                text = "${detailUiState.value.movie?.overview ?: "N/A"}",
                fontSize = 18.sp,
                color = Color.White,
                modifier = Modifier
                    .padding(top = 30.dp),
                fontFamily = jostFontFamily
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            IconButton(
                onClick = {
                    viewModel.addOrRemoveToFavorites(detailUiState.value.movie!!)
                },
                modifier = Modifier
                    .padding(end = 32.dp, top = 22.dp)
                    .size(36.dp)
                    .align(Alignment.End),
                colors = IconButtonColors(
                    containerColor = Color.White,
                    contentColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    disabledContentColor = Color.White.copy(alpha = 0.3f)

                )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.heart),
                    contentDescription = "Back button",
                    modifier = Modifier
                        .size(20.dp),
                    colorFilter = ColorFilter.tint(
                        if (favorites.value.contains(detailUiState.value.movie)) Color.Red else Color.Gray
                    )
                )
            }
        }
    }
}