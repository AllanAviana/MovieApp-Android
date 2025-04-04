package com.example.movieapp_android.data.model

data class Genres(
    val romance: List<Movie> = emptyList(),
    val action: List<Movie> = emptyList(),
    val horror: List<Movie> = emptyList(),
    val suspense: List<Movie> = emptyList(),
    val allMovies: List<Movie> = emptyList(),
    val favorites: List<Movie> = emptyList(),
    val highlights: List<Movie> = emptyList()
)