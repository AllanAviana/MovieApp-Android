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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.example.movieapp_android.R
import com.example.movieapp_android.ui.theme.jostFontFamily

@Composable
fun DetailScreen(navController: NavHostController) {
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
                painter = painterResource(id = R.drawable.detailmage),
                contentDescription = "Detail Image",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Text(
                text = "Godzilla x Kong: Rise together or fall alone",
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
                    Log.d(
                        "DetailScreen",
                        "Back button clicked"
                    )
                },
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(8.dp)
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
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "4.06/10",
                    fontSize = 14.sp,
                    color = Color.White
                )

                Text(
                    text = "1106 votes.",
                    fontSize = 14.sp,
                    color = Color.White
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.25f),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    for (i in 1..3) {
                        Image(
                            imageVector = Icons.Default.Star,
                            contentDescription = "",
                            colorFilter = ColorFilter.tint(Color.Yellow),
                        )
                    }
                }

                Text(
                    text = "2025-02-12",
                    fontSize = 14.sp,
                    color = Color.White
                )
            }

            Text(
                text = "Action, Adventure, Thriller",
                fontSize = 14.sp,
                color = Color.White.copy(alpha = 0.5f),
                modifier = Modifier
                    .padding(top = 6.dp)
            )


            Text(
                text = "Godzilla x Kong: The New Empire entry follows the explosive showdown of Godzilla vs. Kong with an all-new cinematic adventure, pitting the mighty Kong and the fearsome Godzilla against a colossal undiscovered threat hidden within our world, challenging their very existence, and our own. The epic new film will delve further into the histories of these Titans, their origins and the mysteries of Skull Island and beyond, while uncovering the mythic battle that helped forge these extraordinary beings and tied them to humankind forever.",
                fontSize = 14.sp,
                color = Color.White,
                modifier = Modifier
                    .padding(top = 30.dp)
            )
        }
    }
}