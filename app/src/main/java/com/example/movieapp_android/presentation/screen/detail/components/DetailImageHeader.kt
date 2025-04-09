package com.example.movieapp_android.presentation.screen.detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.movieapp_android.R
import com.example.movieapp_android.ui.theme.jostFontFamily

@Composable
fun DetailImageHeader(imagePath: String?, title: String, navController: NavHostController) {
    Box(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(0.3f)
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = "https://image.tmdb.org/t/p/w500/$imagePath"),
            contentDescription = "Detail Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit
        )

        Text(
            text = title,
            color = Color.White.copy(alpha = 0.8f),
            modifier = Modifier.fillMaxWidth().align(Alignment.BottomStart).padding(start = 16.dp).zIndex(1f),
            fontSize = 16.sp,
            fontFamily = jostFontFamily
        )

        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.align(Alignment.TopStart).padding(horizontal = 8.dp, vertical = 16.dp).size(41.dp),
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
                modifier = Modifier.size(24.dp)
            )
        }

        Box(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(0.2f).align(Alignment.BottomStart).background(
                Brush.verticalGradient(
                    colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f))
                )
            )
        )
    }
}