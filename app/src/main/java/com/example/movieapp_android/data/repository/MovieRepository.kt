package com.example.movieapp_android.data.repository

import com.example.movieapp_android.data.api.MovieApi
import com.example.movieapp_android.data.model.MovieResponse
import javax.inject.Inject

class MovieRepository @Inject constructor(private val movieApi: MovieApi) {
    suspend fun getMoviesByGenre(apiKey: String, genreId: Int): MovieResponse {
        return movieApi.getMoviesByGenre(apiKey, genreId)
    }
}