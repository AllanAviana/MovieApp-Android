package com.example.movieapp_android.di

import android.content.Context
import androidx.room.Room
import com.example.movieapp_android.data.contracts.FavoriteRepository
import com.example.movieapp_android.data.contracts.MovieApi
import com.example.movieapp_android.data.contracts.MovieRepository
import com.example.movieapp_android.data.local.AppDatabase
import com.example.movieapp_android.data.contracts.FavoriteMovieDao
import com.example.movieapp_android.data.repository.FavoriteRepositoryImpl
import com.example.movieapp_android.data.repository.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://api.themoviedb.org/3/"

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieApi(retrofit: Retrofit): MovieApi {
        return retrofit.create(MovieApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "movie_db").build()

    @Provides
    @Singleton
    fun provideFavoriteDao(db: AppDatabase): FavoriteMovieDao = db.favoriteMovieDao()

    @Provides @Singleton
    fun provideMovieRepository(api: MovieApi): MovieRepository =
        MovieRepositoryImpl(api)

    @Provides @Singleton
    fun provideFavoriteRepository(
        dao: FavoriteMovieDao
    ): FavoriteRepository = FavoriteRepositoryImpl(dao)

}