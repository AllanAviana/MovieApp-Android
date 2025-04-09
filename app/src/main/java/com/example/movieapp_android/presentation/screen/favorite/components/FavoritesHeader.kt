package com.example.movieapp_android.presentation.screen.favorite.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.movieapp_android.ui.theme.jostFontFamily

@Composable
fun FavoritesHeader() {
    Text(
        text = "Favorites\nMovies",
        color = Color.White,
        fontSize = 46.sp,
        fontFamily = jostFontFamily,
        lineHeight = 50.sp,
        textAlign = TextAlign.Center
    )
}