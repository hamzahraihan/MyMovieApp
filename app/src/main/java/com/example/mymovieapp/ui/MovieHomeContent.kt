package com.example.mymovieapp.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mymovieapp.data.Movie
import com.example.mymovieapp.data.local.LocalMovieDataProvider
import com.example.mymovieapp.ui.theme.MyMovieAppTheme

@Composable
fun MovieAppBar(movieUiState: MovieUiState, modifier: Modifier = Modifier) {
    Box(modifier = Modifier.padding(12.dp)) {
        Text(
            text = movieUiState.currentMovieType.title.uppercase(),
            style = MaterialTheme.typography.titleLarge,
            letterSpacing = 3.0.sp,
        )
    }
}

@Composable
fun MovieList(
    movieUiState: MovieUiState,
    modifier: Modifier = Modifier,
    onMovieCardPressed: (Movie) -> Unit,
) {
    val movies = movieUiState.currentTypeOfMovies
    LazyColumn(
        modifier = modifier,
        contentPadding = WindowInsets.safeDrawing.asPaddingValues(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            MovieAppBar(movieUiState)
        }
        items(movies) { movie ->
            MovieListItem(
                movie = movie,
                selected = false,
                onCardClick = { onMovieCardPressed(movie) },
            )
        }
    }
}

@Composable
private fun MovieListItemImage(@DrawableRes imageRes: Int, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(imageRes),
            contentDescription = null,
            alignment = Alignment.Center,
            contentScale = ContentScale.FillWidth
        )
    }
}

@Composable
private fun MovieListItem(
    movie: Movie,
    selected: Boolean,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(),
        modifier = modifier,
        onClick = onCardClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .size(128.dp)
        ) {
            MovieListItemImage(imageRes = movie.imageRes, modifier = Modifier.size(128.dp))
            Column(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = stringResource(movie.titleRes),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = stringResource(movie.descriptionRes),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 3,
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Category: ${stringResource(movie.categoryRes)}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}


@Preview
@Composable
fun MovieCardPreview() {
    MyMovieAppTheme {
        MovieListItem(
            movie = LocalMovieDataProvider.defaultValue,
            selected = false,
            onCardClick = {},
        )
    }
}
