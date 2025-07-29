package com.example.mymovieapp.ui

import com.example.mymovieapp.data.Movie
import com.example.mymovieapp.data.MovieType
import com.example.mymovieapp.data.local.LocalMovieDataProvider

data class MovieUiState(
    val movies: Map<MovieType, List<Movie>> = emptyMap(),
    val currentMovieType: MovieType = MovieType.POPULAR,
    val currentSelectedMovie: Movie = LocalMovieDataProvider.defaultValue,
    val isShowingHomepage: Boolean = true
) {
    val currentTypeOfMovies: List<Movie> by lazy { movies[currentMovieType] ?: emptyList() }
}