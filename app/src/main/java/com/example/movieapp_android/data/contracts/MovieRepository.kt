package com.example.movieapp_android.data.contracts

import com.example.movieapp_android.data.model.MovieResponse

interface MovieRepository {
    suspend fun getMoviesByGenre(apiKey: String, genreId: Int): MovieResponse
}