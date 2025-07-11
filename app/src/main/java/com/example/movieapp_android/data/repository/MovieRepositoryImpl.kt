package com.example.movieapp_android.data.repository

import com.example.movieapp_android.data.contracts.MovieApi
import com.example.movieapp_android.data.contracts.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi
) : MovieRepository {
    override suspend fun getMoviesByGenre(apiKey: String, genreId: Int) =
        movieApi.getMoviesByGenre(apiKey, genreId)
}
