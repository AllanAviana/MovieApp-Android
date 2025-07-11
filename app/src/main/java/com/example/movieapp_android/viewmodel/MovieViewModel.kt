package com.example.movieapp_android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp_android.data.contracts.FavoriteRepository
import com.example.movieapp_android.data.contracts.MovieRepository
import com.example.movieapp_android.presentation.uistate.DetailUiState
import com.example.movieapp_android.data.model.FavoriteMovie
import com.example.movieapp_android.presentation.uistate.HomeUiState
import com.example.movieapp_android.data.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {

    // -------- Home (genre lists) --------
    private val _genres = MutableStateFlow(HomeUiState())
    val genres: StateFlow<HomeUiState> = _genres.asStateFlow()

    // -------- Details --------
    private val _detail = MutableStateFlow(DetailUiState())
    val detailUiState: StateFlow<DetailUiState> = _detail.asStateFlow()

    // -------- Favourites --------
    private val _favorites = MutableStateFlow<List<Movie>>(emptyList())
    val favorites: StateFlow<List<Movie>> = _favorites.asStateFlow()

    // -------- Highlights --------
    private val _highlights = MutableStateFlow<List<Movie>>(emptyList())
    val highlights: StateFlow<List<Movie>> = _highlights.asStateFlow()

    init {
        viewModelScope.launch {
            // Pre‑load a handful of genres when the ViewModel starts.
            getMoviesByGenre("1f2fc96071583a9c50c89c207132fbd2")

            // Listen continuously for database changes to favourites.
            favoriteRepository.getFavorites().collect { list ->
                _favorites.value = list.map { fav ->
                    // Map DB entity ► domain model expected by the UI.
                    Movie(
                        id = fav.id,
                        title = fav.title,
                        poster_path = fav.poster_path,
                        vote_average = fav.vote_average,
                        genre_ids = fav.genre_ids,
                        adult = fav.adult,
                        backdrop_path = fav.backdrop_path,
                        original_language = fav.original_language,
                        original_title = fav.original_title,
                        overview = fav.overview,
                        popularity = fav.popularity,
                        release_date = fav.release_date,
                        video = fav.video,
                        vote_count = fav.vote_count
                    )
                }
            }
        }
    }

    /**
     * Fetches movies for several hard‑coded genre IDs, merging them into the UI state.
     * Also selects a random movie for the hero banner and marks the UI as *success* after a small delay.
     */
    suspend fun getMoviesByGenre(apiKey: String) {
        var response = movieRepository.getMoviesByGenre(apiKey, 10749) // Romance
        _genres.update { current ->
            val newMovies = response.results.filterNot { movie ->
                movie.id in current.allMovies.map { it.id }
            }
            current.copy(
                romance = newMovies,
                allMovies = current.allMovies + newMovies
            )
        }

        response = movieRepository.getMoviesByGenre(apiKey, 27) // Horror
        _genres.update { current ->
            val newMovies = response.results.filterNot { movie ->
                movie.id in current.allMovies.map { it.id }
            }
            current.copy(
                horror = newMovies,
                allMovies = current.allMovies + newMovies
            )
        }

        response = movieRepository.getMoviesByGenre(apiKey, 28) // Action
        _genres.update { current ->
            val newMovies = response.results.filterNot { movie ->
                movie.id in current.allMovies.map { it.id }
            }
            current.copy(
                action = newMovies,
                allMovies = current.allMovies + newMovies
            )
        }

        response = movieRepository.getMoviesByGenre(apiKey, 53) // Thriller
        _genres.update { current ->
            val newMovies = response.results.filterNot { movie ->
                movie.id in current.allMovies.map { it.id }
            }
            current.copy(
                suspense = newMovies,
                allMovies = current.allMovies + newMovies,
                randomMovie = current.allMovies.random()
            )
        }

        highlightMovie()

        delay(3000)
        _genres.update { current -> current.copy(isSuccess = true) }
    }

    /** Store the movie to be shown on the Detail screen. */
    fun updateDetailUiState(movie: Movie) {
        _detail.update { it.copy(movie = movie) }
    }

    /** Convert TMDB genre IDs to readable English names. */
    fun genresMovie(genre: List<Int>): String {
        val genreMap = mapOf(
            28 to "Action", 12 to "Adventure", 16 to "Animation", 35 to "Comedy", 80 to "Crime",
            99 to "Documentary", 18 to "Drama", 10751 to "Family", 14 to "Fantasy", 36 to "History",
            27 to "Horror", 10402 to "Music", 9648 to "Mystery", 10749 to "Romance", 878 to "Science Fiction",
            10770 to "TV Movie", 53 to "Thriller", 10752 to "War", 37 to "Western"
        )
        return genre.joinToString(", ") { id -> genreMap[id] ?: "Unknown" }
    }

    /** Toggle a movie in / out of the favourites table. */
    fun addOrRemoveToFavorites(movie: Movie) {
        viewModelScope.launch {
            val isFavorite = _favorites.value.any { it.id == movie.id }

            val favoriteEntity = FavoriteMovie(
                adult = movie.adult,
                backdrop_path = movie.backdrop_path,
                genre_ids = movie.genre_ids,
                id = movie.id,
                original_language = movie.original_language,
                original_title = movie.original_title,
                overview = movie.overview,
                popularity = movie.popularity,
                poster_path = movie.poster_path,
                release_date = movie.release_date,
                title = movie.title,
                video = movie.video,
                vote_average = movie.vote_average,
                vote_count = movie.vote_count
            )
            if (isFavorite) {
                favoriteRepository.remove(favoriteEntity)
            } else {
                favoriteRepository.add(favoriteEntity)
            }
        }
    }

    /** Select highlight movies based on rating & vote count. */
    fun highlightMovie() {
        val movies = _genres.value.allMovies.filter { m ->
            m.vote_average >= 7.0 && m.vote_count >= 10
        }
        if (movies.isNotEmpty()) _highlights.update { movies }
    }
}
