package com.example.movieapp_android.data.repository

import com.example.movieapp_android.data.contracts.FavoriteRepository
import com.example.movieapp_android.data.contracts.FavoriteMovieDao
import com.example.movieapp_android.data.model.FavoriteMovie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val dao: FavoriteMovieDao
) : FavoriteRepository {

    override fun getFavorites(): Flow<List<FavoriteMovie>> =
        dao.getAllFavorites()

    override suspend fun add(movie: FavoriteMovie) =
        dao.addToFavorites(movie)

    override suspend fun remove(movie: FavoriteMovie) =
        dao.removeFromFavorites(movie)
}

