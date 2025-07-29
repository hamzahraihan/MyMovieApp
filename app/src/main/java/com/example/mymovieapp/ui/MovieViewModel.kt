package com.example.mymovieapp.ui

import androidx.lifecycle.ViewModel
import com.example.mymovieapp.data.Movie
import com.example.mymovieapp.data.MovieType
import com.example.mymovieapp.data.local.LocalMovieDataProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MovieViewModel : ViewModel() {
    private val _uiState: MutableStateFlow<MovieUiState> = MutableStateFlow(MovieUiState())
    val uiState: StateFlow<MovieUiState> = _uiState

    init {
        initializeUiState()
    }

    private fun initializeUiState() {
        val movies: Map<MovieType, List<Movie>> = LocalMovieDataProvider.getMovieData().groupBy {
            it.movieType
        }
        _uiState.value = MovieUiState(
            movies = movies,
            currentSelectedMovie = movies[MovieType.POPULAR]?.get(0)
                ?: LocalMovieDataProvider.defaultValue
        )
    }

    fun updateDetailsScreenStates(movie: Movie) {
        _uiState.update {
            it.copy(
                currentSelectedMovie = movie,
                isShowingHomepage = false
            )
        }
    }

    fun restartHomeScreenState() {
        _uiState.update {
            it.copy(
                currentSelectedMovie = it.movies[it.currentMovieType]?.get(0)
                    ?: LocalMovieDataProvider.defaultValue,
                isShowingHomepage = true
            )
        }
    }

    fun updateCurrentMovie(movieType: MovieType) {
        _uiState.update {
            it.copy(
                currentMovieType = movieType
            )
        }
    }
}