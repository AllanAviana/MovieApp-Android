@file:OptIn(ExperimentalCoroutinesApi::class)


package com.example.movieapp_android

import com.example.movieapp_android.data.contracts.FavoriteRepository
import com.example.movieapp_android.data.contracts.MovieRepository
import com.example.movieapp_android.data.model.FavoriteMovie
import com.example.movieapp_android.data.model.Movie
import com.example.movieapp_android.data.model.MovieResponse
import com.example.movieapp_android.viewmodel.MovieViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MovieViewModelTest {

    private val dispatcher = StandardTestDispatcher()
    private lateinit var movieRepo: FakeMovieRepository
    private lateinit var favRepo: FakeFavoriteRepository
    private lateinit var vm: MovieViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        movieRepo = FakeMovieRepository()
        favRepo   = FakeFavoriteRepository()
        vm        = MovieViewModel(movieRepo, favRepo)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    // ----------------------------------------------------------------------
    // 1. genresMovie() mapping ------------------------------------------------
    // ----------------------------------------------------------------------
    @Test
    fun genresMovie_mapsIdsToNames() = runTest(dispatcher) {
        val result = vm.genresMovie(listOf(28, 35))
        assertEquals("Action, Comedy", result)
    }

    // ----------------------------------------------------------------------
    // 2. updateDetailUiState() ----------------------------------------------
    // ----------------------------------------------------------------------
    @Test
    fun updateDetailUiState_setsSelectedMovie() = runTest(dispatcher) {
        val movie = sampleMovie(id = 1, title = "Interstellar")
        vm.updateDetailUiState(movie)
        assertEquals(movie, vm.detailUiState.value.movie)
    }

    // ----------------------------------------------------------------------
    // 3. addOrRemoveToFavorites() toggles correctly --------------------------
    // ----------------------------------------------------------------------
    @Test
    fun addOrRemoveToFavorites_togglesFavoriteState() = runTest(dispatcher) {
        val movie = sampleMovie(id = 123)

        // First tap → add
        vm.addOrRemoveToFavorites(movie)
        advanceUntilIdle()
        assertTrue(favRepo.addCalled)
        assertFalse(favRepo.removeCalled)

        // Second tap → remove
        vm.addOrRemoveToFavorites(movie)
        advanceUntilIdle()
        assertTrue(favRepo.removeCalled)
    }

    // ----------------------------------------------------------------------
    // 4. highlightMovie() filters correctly ---------------------------------
    // ----------------------------------------------------------------------
    @Test
    fun highlightMovie_returnsHighlyRatedMovies() = runTest(dispatcher) {
        val good = sampleMovie(id = 1, voteAverage = 8.0, voteCount = 20)
        val bad  = sampleMovie(id = 2, voteAverage = 5.0, voteCount = 5)

        vm.genres.value.allMovies += listOf(good, bad)

        vm.highlightMovie()

        assertTrue(vm.highlights.value.contains(good))
        assertFalse(vm.highlights.value.contains(bad))
    }


    // ----------------------------------------------------------------------
    // 5. getMoviesByGenre() populates UI state ------------------------------
    // ----------------------------------------------------------------------
    @Test
    fun getMoviesByGenre_populatesAndSetsSuccess() = runTest(dispatcher) {
        vm.getMoviesByGenre("fake-key")
        advanceTimeBy(3_000)   // pula o delay(3000)
        advanceUntilIdle()

        val state = vm.genres.value
        assertTrue(state.allMovies.isNotEmpty())   // lista geral populada
        assertTrue(state.isSuccess)                // flag acionada

        // IDs sem duplicatas
        val ids = state.allMovies.map { it.id }
        assertEquals(ids.distinct().size, ids.size)
    }


    // ----------------------------------------------------------------------
    // Helper data & fakes ----------------------------------------------------
    // ----------------------------------------------------------------------

    private fun sampleMovie(
        id: Int = 0,
        title: String = "Movie $id",
        voteAverage: Double = 7.5,
        voteCount: Int = 15
    ) = Movie(
        id = id,
        title = title,
        poster_path = "path$id.jpg",
        vote_average = voteAverage,
        genre_ids = listOf(28),
        adult = false,
        backdrop_path = null,
        original_language = "en",
        original_title = title,
        overview = "overview",
        popularity = 100.0,
        release_date = "2025-01-01",
        video = false,
        vote_count = voteCount
    )

    private class FakeMovieRepository : MovieRepository {
        override suspend fun getMoviesByGenre(apiKey: String, genreId: Int): MovieResponse {
            val movies = List(3) { idx ->
                Movie(
                    id = genreId * 100 + idx,
                    title = "Movie$idx",
                    poster_path = "p$idx.jpg",
                    vote_average = 7.0 + idx,
                    genre_ids = listOf(genreId),
                    adult = false,
                    backdrop_path = null,
                    original_language = "en",
                    original_title = "Movie$idx",
                    overview = "overview",
                    popularity = 50.0,
                    release_date = "2025-01-0${idx + 1}",
                    video = false,
                    vote_count = 20 + idx
                )
            }
            return MovieResponse(
                page = 1,
                total_pages = 1,
                total_results = movies.size,
                results = movies
            )        }
    }

    private class FakeFavoriteRepository : FavoriteRepository {
        private val flow = MutableSharedFlow<List<FavoriteMovie>>(replay = 1)
        var addCalled = false
        var removeCalled = false

        init { flow.tryEmit(emptyList()) }

        override fun getFavorites(): Flow<List<FavoriteMovie>> = flow

        override suspend fun add(movie: FavoriteMovie) {
            addCalled = true
            val current = (flow.replayCache.firstOrNull() ?: emptyList()) + movie
            flow.emit(current)
        }

        override suspend fun remove(movie: FavoriteMovie) {
            removeCalled = true
            val current = (flow.replayCache.firstOrNull() ?: emptyList()).filterNot { it.id == movie.id }
            flow.emit(current)
        }
    }
}
