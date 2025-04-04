package com.example.movieapp_android.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp_android.data.model.Genres
import com.example.movieapp_android.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository
): ViewModel() {

    private val _genres = MutableStateFlow(Genres())
    val genres: StateFlow<Genres> = _genres.asStateFlow()

    init {
        viewModelScope.launch {
            getMoviesByGenre("1f2fc96071583a9c50c89c207132fbd2")
        }
    }

    suspend fun getMoviesByGenre(apiKey: String){
        var response = movieRepository.getMoviesByGenre(apiKey, 10749)

        _genres.update {
            it.copy(
                romance = response.results.filter { movie ->
                    movie.id !in genres.value.allMovies.map { it.id }
                },
                allMovies = response.results.filter { movie ->
                    movie.id !in genres.value.allMovies.map { it.id }
                }
            )
        }

        response = movieRepository.getMoviesByGenre(apiKey, 27)
        _genres.update {
            it.copy(
                horror = response.results.filter { movie ->
                    movie.id !in genres.value.allMovies.map { it.id }
                },
                allMovies = response.results.filter { movie ->
                    movie.id !in genres.value.allMovies.map { it.id }
                }
            )
        }

        response = movieRepository.getMoviesByGenre(apiKey, 28)
        _genres.update {
            it.copy(
                action = response.results.filter { movie ->
                    movie.id !in genres.value.allMovies.map { it.id }
                },
                allMovies = response.results.filter { movie ->
                    movie.id !in genres.value.allMovies.map { it.id }
                }
            )
        }

        response = movieRepository.getMoviesByGenre(apiKey, 53)
        _genres.update {
            it.copy(
                suspense = response.results.filter { movie ->
                    movie.id !in genres.value.allMovies.map { it.id }
                },
                allMovies = response.results.filter { movie ->
                    movie.id !in genres.value.allMovies.map { it.id }
                }
            )
        }

        Log.d("MovieViewModel", "Romance: ${_genres.value.romance}")

    }
}
