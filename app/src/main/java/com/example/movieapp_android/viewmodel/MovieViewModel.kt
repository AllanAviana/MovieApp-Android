package com.example.movieapp_android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp_android.data.model.DetailUiState
import com.example.movieapp_android.data.model.HomeUiState
import com.example.movieapp_android.data.model.Movie
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

    private val _genres = MutableStateFlow(HomeUiState())
    val genres: StateFlow<HomeUiState> = _genres.asStateFlow()

    private val _detail = MutableStateFlow(DetailUiState())
    val detailUiState: StateFlow<DetailUiState> = _detail.asStateFlow()

    private val _favorites = MutableStateFlow<List<Movie>>(emptyList())
    val favorites: StateFlow<List<Movie>> = _favorites.asStateFlow()



    init {
        viewModelScope.launch {
            getMoviesByGenre("1f2fc96071583a9c50c89c207132fbd2")
        }
    }

    suspend fun getMoviesByGenre(apiKey: String) {
        var response = movieRepository.getMoviesByGenre(apiKey, 10749)
        _genres.update { current ->
            val newMovies = response.results.filter { movie ->
                movie.id !in current.allMovies.map { it.id }
            }
            current.copy(
                romance = newMovies,
                allMovies = current.allMovies + newMovies
            )
        }

        response = movieRepository.getMoviesByGenre(apiKey, 27)
        _genres.update { current ->
            val newMovies = response.results.filter { movie ->
                movie.id !in current.allMovies.map { it.id }
            }
            current.copy(
                horror = newMovies,
                allMovies = current.allMovies + newMovies
            )
        }

        response = movieRepository.getMoviesByGenre(apiKey, 28)
        _genres.update { current ->
            val newMovies = response.results.filter { movie ->
                movie.id !in current.allMovies.map { it.id }
            }
            current.copy(
                action = newMovies,
                allMovies = current.allMovies + newMovies
            )
        }

        response = movieRepository.getMoviesByGenre(apiKey, 53)
        _genres.update { current ->
            val newMovies = response.results.filter { movie ->
                movie.id !in current.allMovies.map { it.id }
            }
            current.copy(
                suspense = newMovies,
                allMovies = current.allMovies + newMovies,
                randomMovie = current.allMovies.random()
            )
        }
    }

    fun updateDetailUiState(movie: Movie) {
        _detail.update { current ->
            current.copy(
                movie = movie
            )
        }
    }

    fun genresMovie(genre: List<Int>): String {
        val genreMap = mapOf(
            28 to "Action",
            12 to "Adventure",
            16 to "Animation",
            35 to "Comedy",
            80 to "Crime",
            99 to "Documentary",
            18 to "Drama",
            10751 to "Family",
            14 to "Fantasy",
            36 to "History",
            27 to "Horror",
            10402 to "Music",
            9648 to "Mystery",
            10749 to "Romance",
            878 to "Science Fiction",
            10770 to "TV Movie",
            53 to "Thriller",
            10752 to "War",
            37 to "Western"
        )

        return genre.joinToString(", ") { id ->
            genreMap[id] ?: "Unknown"
        }
    }

    fun addOrRemoveToFavorites(movie: Movie) {
        if (_favorites.value.contains(movie)) {
            _favorites.update { current ->
                current.filter { it.id != movie.id }
            }
        } else {
            _favorites.update { current ->
                current + movie
            }
        }
    }
}
