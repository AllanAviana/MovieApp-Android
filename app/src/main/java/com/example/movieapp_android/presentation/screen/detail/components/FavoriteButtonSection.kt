package com.example.movieapp_android.presentation.screen.detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.movieapp_android.R
import com.example.movieapp_android.data.model.Movie
import com.example.movieapp_android.viewmodel.MovieViewModel

@Composable
fun FavoriteButtonSection(viewModel: MovieViewModel, movie: Movie?, favorites: List<Movie>) {
    Column(
        modifier = Modifier.fillMaxSize().padding(bottom = 16.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        IconButton(
            onClick = { movie?.let { viewModel.addOrRemoveToFavorites(it) } },
            modifier = Modifier.padding(end = 32.dp, top = 22.dp).size(36.dp).align(Alignment.End),
            colors = IconButtonColors(
                containerColor = Color.White,
                contentColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                disabledContentColor = Color.White.copy(alpha = 0.3f)
            )
        ) {
            Image(
                painter = painterResource(id = R.drawable.heart),
                contentDescription = "Favorite button",
                modifier = Modifier.size(20.dp),
                colorFilter = ColorFilter.tint(
                    if (favorites.any { it.id == movie?.id }) Color.Red else Color.Gray
                )
            )
        }
    }
}