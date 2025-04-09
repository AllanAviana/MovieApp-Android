package com.example.movieapp_android.presentation.screen.highlight

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.movieapp_android.ui.theme.jostFontFamily
import com.example.movieapp_android.viewmodel.MovieViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HighlightScreen(viewModel: MovieViewModel, navController: NavHostController) {
    val highlights = viewModel.highlights.collectAsState()
    val pagerState = rememberPagerState(pageCount = { highlights.value.size })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Highlights",
            color = Color.White,
            fontSize = 56.sp,
            fontFamily = jostFontFamily,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .height(600.dp)
                .clip(RoundedCornerShape(48.dp))
        ) { page ->
            val movie = highlights.value[page]
            Column() {
                Image(
                    painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500/${movie.poster_path}"),
                    contentDescription = movie.title,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            viewModel.updateDetailUiState(movie)
                            navController.navigate("detail_screen")
                        },
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = movie.title,
                    fontSize = 18.sp,
                    color = Color.White,
                    fontFamily = jostFontFamily,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

