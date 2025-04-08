package com.example.movieapp_android.presentation.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.movieapp_android.R
import com.example.movieapp_android.data.model.Movie
import com.example.movieapp_android.ui.theme.jostFontFamily
import com.example.movieapp_android.viewmodel.MovieViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HomeScreen(navController: NavHostController, viewModel: MovieViewModel) {
    val homeUiStateState = viewModel.genres.collectAsState()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(start = 16.dp)
    ) {
        item {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .width(147.dp)
                    .height(41.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box() {
                Image(
                    painter = rememberAsyncImagePainter(model = "https://image.tmdb.org/t/p/w500/${homeUiStateState.value.randomMovie?.poster_path}"),
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
                text = homeUiStateState.value.randomMovie?.title ?: "N/A",
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(top = 8.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            GenreRow(
                genre = "Romance",
                movies = homeUiStateState.value.romance,
                navigation = { navController.navigate("detail_screen") },
                viewModel = viewModel
            )
            GenreRow(
                genre = "Horro",
                movies = homeUiStateState.value.horror,
                navigation = { navController.navigate("detail_screen") },
                viewModel = viewModel
            )
            GenreRow(
                genre = "Action",
                movies = homeUiStateState.value.action,
                navigation = { navController.navigate("detail_screen") },
                viewModel = viewModel
            )
            GenreRow(
                genre = "Suspene",
                movies = homeUiStateState.value.suspense,
                navigation = { navController.navigate("detail_screen") },
                viewModel = viewModel
            )
        }
    }
}

@Composable
fun GenreRow(genre: String, movies: List<Movie>, navigation: () -> Unit, viewModel: MovieViewModel) {
    Text(
        text = genre,
        color = Color.White.copy(alpha = 0.8f),
        fontSize = 20.sp,
        modifier = Modifier
            .padding(vertical = 8.dp),
        fontFamily = jostFontFamily
    )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(end = 16.dp, bottom = 8.dp)
        ) {
            items(movies) { movie ->
                Column(
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(model = "https://image.tmdb.org/t/p/w500/${movie.poster_path}"),
                        contentDescription = "",
                        modifier = Modifier
                            .width(136.dp)
                            .height(202.dp)
                            .clickable {
                                navigation()
                                viewModel.updateDetailUiState(movie)
                            }
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
        }
}