package com.example.mymovieapp.ui

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mymovieapp.data.Movie
import com.example.mymovieapp.data.MovieType
import com.example.mymovieapp.ui.theme.MyMovieAppTheme
import com.example.mymovieapp.util.MovieContentType
import com.example.mymovieapp.util.MovieNavigationType

@Composable
fun MovieApp(
    viewModel: MovieViewModel = viewModel(),
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier
) {
    val movieUiState = viewModel.uiState.collectAsState().value
    val contentType: MovieContentType
    val navigationType: MovieNavigationType

    when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            navigationType = MovieNavigationType.BOTTOM_NAVIGATION
            contentType = MovieContentType.LIST_ONLY
        }

        WindowWidthSizeClass.Medium -> {
            navigationType = MovieNavigationType.NAVIGATION_RAIL
            contentType = MovieContentType.LIST_ONLY
        }

        WindowWidthSizeClass.Expanded -> {
            navigationType = MovieNavigationType.PERMANENT_NAVIGATION_DRAWER
            contentType = MovieContentType.LIST_AND_DETAIL
        }

        else -> {
            navigationType = MovieNavigationType.BOTTOM_NAVIGATION
            contentType = MovieContentType.LIST_ONLY
        }
    }
    MovieHomeScreen(
        navigationType = navigationType,
        contentType = contentType,
        movieUiState = movieUiState,
        onTabPressed = { movieType: MovieType ->
            viewModel.updateCurrentMovie(movieType = movieType)
            viewModel.restartHomeScreenState()
        },
        onMovieCardPressed = { movie: Movie ->
            viewModel.updateDetailsScreenStates(
                movie = movie
            )
        },
        onDetailScreenBackPressed = {
            viewModel.restartHomeScreenState()
        },
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun MovieListPreview() {
    MyMovieAppTheme {
        MovieApp(
            windowSize = WindowWidthSizeClass.Compact,
        )
    }
}
