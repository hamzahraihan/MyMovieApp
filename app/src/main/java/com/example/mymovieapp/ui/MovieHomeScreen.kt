package com.example.mymovieapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.mymovieapp.data.MovieType
import com.example.mymovieapp.data.local.LocalMovieDataProvider

@Composable
fun MovieHomeScreen(contentPadding: PaddingValues) {
    val navigationItemContentList = listOf(
        NavigationItemContent(
            movieType = MovieType.POPULAR,
            icon = Icons.Default.Star,
            text = "Popular"
        ),
        NavigationItemContent(
            movieType = MovieType.TOP_RATED,
            icon = Icons.Default.FavoriteBorder,
            text = "Top Rated"
        ),
        NavigationItemContent(
            movieType = MovieType.NOW_PLAYING,
            icon = Icons.Default.PlayArrow,
            text = "Now Playing"
        ),
        NavigationItemContent(
            movieType = MovieType.UPCOMING,
            icon = Icons.Default.DateRange,
            text = "Upcoming"
        )
    )
    MovieAppContent(
        navigationItemContentList = navigationItemContentList,
        contentPadding = contentPadding
    )
}

@Composable
private fun MovieAppContent(
    navigationItemContentList: List<NavigationItemContent>,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(top = contentPadding.calculateTopPadding())
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.inverseOnSurface)
        ) {
            MovieList(
                movies = LocalMovieDataProvider.getMovieData(),
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
                    .padding(top = 40.dp),
            )
            MovieNavigationBottomBar(
                currentTab = MovieType.POPULAR,
                navigationItemContentList = navigationItemContentList,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MovieNavigationBottomBar(
    currentTab: MovieType,
    navigationItemContentList: List<NavigationItemContent>,
    modifier: Modifier
) {
    NavigationBar(modifier = modifier) {
        for (navItem in navigationItemContentList) {
            NavigationBarItem(
                selected = currentTab == navItem.movieType,
                onClick = {},
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = navItem.text
                    )
                },
            )
        }

    }

}

private data class NavigationItemContent(
    val movieType: MovieType,
    val icon: ImageVector,
    val text: String
)