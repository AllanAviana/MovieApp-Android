package com.example.movieapp_android.data.local

import androidx.room.*
import com.example.movieapp_android.data.model.FavoriteMovie
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteMovieDao {
    @Query("SELECT * FROM favorite_movies")
    fun getAllFavorites(): Flow<List<FavoriteMovie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorites(movie: FavoriteMovie)

    @Delete
    suspend fun removeFromFavorites(movie: FavoriteMovie)
}