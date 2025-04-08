package com.example.movieapp_android.data.model

data class HomeUiState(
    val romance: List<Movie> = emptyList(),
    val action: List<Movie> = emptyList(),
    val horror: List<Movie> = emptyList(),
    val suspense: List<Movie> = emptyList(),
    val allMovies: List<Movie> = emptyList(),
    val favorites: List<Movie> = emptyList(),
    val highlights: List<Movie> = emptyList(),
    val randomMovie: Movie? = Movie(
        adult = false,
        backdrop_path = "",
        genre_ids = emptyList(),
        id = 0,
        original_language = "",
        original_title = "",
        overview = "",
        popularity = 0.0,
        poster_path = "",
        release_date = "",
        title = "",
        video = false,
        vote_average = 0.0,
        vote_count = 0
    ),
)