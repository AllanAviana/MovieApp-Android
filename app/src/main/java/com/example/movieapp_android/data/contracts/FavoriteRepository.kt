package com.example.movieapp_android.data.contracts

import com.example.movieapp_android.data.model.FavoriteMovie
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun getFavorites(): Flow<List<FavoriteMovie>>
    suspend fun add(movie: FavoriteMovie)
    suspend fun remove(movie: FavoriteMovie)
}
