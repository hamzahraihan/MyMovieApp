package com.example.mymovieapp.ui

import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mymovieapp.ui.theme.MyMovieAppTheme

@Composable
fun MovieDetailScreen(
    movieUiState: MovieUiState,
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit
) {
    BackHandler {
        onBackPressed()
    }

    val selectedMovie = movieUiState.currentSelectedMovie
    Box(modifier = modifier) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.inverseOnSurface)
        ) {
            item {
                Box {
                    MovieDetailScreenImage(imageRes = selectedMovie.imageRes)
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .matchParentSize(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        MovieDetailScreenTopAppBar(
                            modifier = Modifier.padding(top = 16.dp),
                            onBackPressed = onBackPressed
                        )
                        Spacer(modifier = Modifier.weight(2f))
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(30.dp)
                        ) {
                            MovieDetailScreenTitle(selectedMovie.titleRes)
                            MovieDetailButtons()
                        }
                    }
                }
                MovieDetailScreenDescription(
                    modifier = Modifier.padding(16.dp),
                    descriptionRes = selectedMovie.descriptionRes
                )
            }
        }
    }
}

@Composable
fun MovieDetailScreenImage(modifier: Modifier = Modifier, @DrawableRes imageRes: Int) {
    Image(
        painter = painterResource(imageRes),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .graphicsLayer {
                compositingStrategy = CompositingStrategy.Offscreen
            }
            .drawWithContent {
                drawContent()
                drawRect(
                    brush = Brush.verticalGradient(
                        0.5f to Color.Black,
                        1f to Color.Transparent
                    ),
                    blendMode = BlendMode.DstIn
                )
            }
    )
}

@Composable
fun MovieDetailScreenTitle(@StringRes titleRes: Int) {
    Text(
        text = stringResource(titleRes),
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        textAlign = TextAlign.Center,
    )
}

@Composable
fun MovieDetailScreenDescription(modifier: Modifier = Modifier, @StringRes descriptionRes: Int) {
    Text(
        text = stringResource(descriptionRes),
        style = MaterialTheme.typography.bodyMedium,
        color = Color.White,
        modifier = modifier
    )
}

@Composable
fun MovieDetailScreenTopAppBar(modifier: Modifier = Modifier, onBackPressed: () -> Unit) {
    IconButton(
        onClick = onBackPressed,
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.inverseOnSurface,
                shape = CircleShape
            )
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            tint = MaterialTheme.colorScheme.onSurface,
            contentDescription = null
        )
    }
}

@Composable
fun MovieDetailButtons(modifier: Modifier = Modifier, buttons: Buttons = Buttons) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(35.dp),
        modifier = modifier
    ) {
        for (buttonIcon in buttons.button) {
            IconButton(
                onClick = {},
                modifier = Modifier.background(
                    MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(14.dp)
                )
            ) {
                Icon(
                    imageVector = buttonIcon,
                    tint = MaterialTheme.colorScheme.onSurface,
                    contentDescription = null
                )
            }
        }
    }
}

object Buttons {
    val button: List<ImageVector> = listOf(
        Icons.Default.FavoriteBorder,
        Icons.Default.Share,
        Icons.Default.MoreVert,
    )
}

@Preview(showBackground = true)
@Composable
fun MovieDetailScreenPreview() {
    MyMovieAppTheme(darkTheme = true) {
        val viewModel: MovieViewModel = viewModel()
        val movieUiState = viewModel.uiState.collectAsState().value
        MovieDetailScreen(movieUiState = movieUiState, onBackPressed = {})
    }
}