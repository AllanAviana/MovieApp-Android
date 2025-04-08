package com.example.movieapp_android.data.repository

import com.example.movieapp_android.data.local.FavoriteMovieDao
import com.example.movieapp_android.data.model.FavoriteMovie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteRepository @Inject constructor(
    private val dao: FavoriteMovieDao
) {
    fun getFavorites(): Flow<List<FavoriteMovie>> = dao.getAllFavorites()

    suspend fun add(movie: FavoriteMovie) = dao.addToFavorites(movie)

    suspend fun remove(movie: FavoriteMovie) = dao.removeFromFavorites(movie)
}
