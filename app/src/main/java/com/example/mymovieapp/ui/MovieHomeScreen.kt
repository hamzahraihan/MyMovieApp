package com.example.mymovieapp.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
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
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.mymovieapp.data.Movie
import com.example.mymovieapp.data.MovieType
import com.example.mymovieapp.util.MovieContentType
import com.example.mymovieapp.util.MovieNavigationType


@Composable
fun MovieHomeScreen(
    navigationType: MovieNavigationType,
    contentType: MovieContentType,
    movieUiState: MovieUiState,
    onTabPressed: (MovieType) -> Unit,
    onMovieCardPressed: (Movie) -> Unit,
    onDetailScreenBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
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
    if (navigationType == MovieNavigationType.PERMANENT_NAVIGATION_DRAWER) {
        PermanentNavigationDrawer(
            drawerContent = {
                PermanentDrawerSheet(
                    modifier = Modifier.width(240.dp),
                    drawerContentColor = MaterialTheme.colorScheme.inverseOnSurface
                ) {
                    NavigationDrawerContent(
                        selectedDestination = movieUiState.currentMovieType,
                        onTabPressed = onTabPressed,
                        navigationItemContentList = navigationItemContentList,
                        modifier = Modifier
                            .wrapContentWidth()
                            .fillMaxHeight()
                            .background(MaterialTheme.colorScheme.inverseOnSurface)
                            .padding(12.dp)
                    )
                }
            },
        ) {
            MovieAppContent(
                navigationType = navigationType,
                contentType = contentType,
                movieUiState = movieUiState,
                onTabPressed = onTabPressed,
                onMovieCardPressed = onMovieCardPressed,
                navigationItemContentList = navigationItemContentList,
                modifier = modifier
            )
        }
    } else {
        if (movieUiState.isShowingHomepage) {
            MovieAppContent(
                navigationType = navigationType,
                contentType = contentType,
                movieUiState = movieUiState,
                onTabPressed = onTabPressed,
                onMovieCardPressed = onMovieCardPressed,
                navigationItemContentList = navigationItemContentList,
                modifier = modifier
            )
        } else {
            MovieDetailScreen(
                onBackPressed = onDetailScreenBackPressed,
                movieUiState = movieUiState,
            )
        }
    }
}

@Composable
private fun MovieAppContent(
    navigationType: MovieNavigationType,
    contentType: MovieContentType,
    movieUiState: MovieUiState,
    onTabPressed: (MovieType) -> Unit,
    onMovieCardPressed: (Movie) -> Unit,
    navigationItemContentList: List<NavigationItemContent>,
    modifier: Modifier = Modifier
) {
    val movies = movieUiState.currentTypeOfMovies
    Box(
        modifier = modifier
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.inverseOnSurface),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (contentType == MovieContentType.LIST_AND_DETAIL) {
                    MovieListAndDetail(
                        movieUiState = movieUiState,
                        onMovieCardPressed = onMovieCardPressed,
                        modifier = Modifier
                            .statusBarsPadding()
                            .weight(2f)
                    )
                } else {
                    MovieList(
                        movieUiState = movieUiState,
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 16.dp),
                        onMovieCardPressed = onMovieCardPressed,
                    )
                }
                AnimatedVisibility(visible = navigationType == MovieNavigationType.BOTTOM_NAVIGATION) {
                    MovieNavigationBottomBar(
                        currentTab = movieUiState.currentMovieType,
                        onTabPressed = onTabPressed,
                        navigationItemContentList = navigationItemContentList,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
private fun NavigationDrawerContent(
    selectedDestination: MovieType,
    onTabPressed: (MovieType) -> Unit,
    navigationItemContentList: List<NavigationItemContent>,
    modifier: Modifier
) {
    Column(modifier = modifier) {
        for (navItem in navigationItemContentList) {
            NavigationDrawerItem(
                selected = selectedDestination == navItem.movieType,
                label = {
                    Text(
                        text = navItem.text,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                },
                onClick = {
                    onTabPressed(navItem.movieType)
                },
                icon = {
                    Icon(imageVector = navItem.icon, contentDescription = null)

                },
                colors = NavigationDrawerItemDefaults.colors(
                    unselectedContainerColor = Color.Transparent
                ),
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MovieNavigationBottomBar(
    currentTab: MovieType,
    onTabPressed: (MovieType) -> Unit,
    navigationItemContentList: List<NavigationItemContent>,
    modifier: Modifier
) {
    NavigationBar(modifier = modifier) {
        for (navItem in navigationItemContentList) {
            NavigationBarItem(
                selected = currentTab == navItem.movieType,
                onClick = { onTabPressed(navItem.movieType) },
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