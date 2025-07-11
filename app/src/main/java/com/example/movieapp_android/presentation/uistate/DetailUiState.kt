package com.example.movieapp_android.presentation.uistate

import com.example.movieapp_android.data.model.Movie

data class DetailUiState(
    val movie: Movie? = Movie(
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
    val isLoading: Boolean = false,
    val errorMessage: String? = null

)
