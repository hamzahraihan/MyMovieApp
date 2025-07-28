package com.example.mymovieapp.data.local

import com.example.mymovieapp.data.Movie
import com.example.mymovieapp.R
import com.example.mymovieapp.data.MovieType

object LocalMovieDataProvider {
    val defaultValue = getMovieData()[0]

    fun getMovieData(): List<Movie> {
        return listOf(
            Movie(
                id = 0,
                titleRes = R.string.title1,
                descriptionRes = R.string.description1,
                categoryRes = R.string.category1,
                imageRes = R.drawable.history1,
                movieType = MovieType.POPULAR
            ),
            Movie(
                id = 1,
                titleRes = R.string.title2,
                descriptionRes = R.string.description2,
                categoryRes = R.string.category2,
                imageRes = R.drawable.action1,
                movieType = MovieType.POPULAR
            ),
            Movie(
                id = 2,
                titleRes = R.string.title3,
                descriptionRes = R.string.description3,
                categoryRes = R.string.category3,
                imageRes = R.drawable.sciencefic1,
                movieType = MovieType.POPULAR
            ),
            Movie(
                id = 3,
                titleRes = R.string.title4,
                descriptionRes = R.string.description4,
                categoryRes = R.string.description4,
                imageRes = R.drawable.horror1,
                movieType = MovieType.POPULAR
            ),
            Movie(
                id = 0,
                titleRes = R.string.title1,
                descriptionRes = R.string.description1,
                categoryRes = R.string.category1,
                imageRes = R.drawable.history1,
                movieType = MovieType.POPULAR
            ),
            Movie(
                id = 1,
                titleRes = R.string.title2,
                descriptionRes = R.string.description2,
                categoryRes = R.string.category2,
                imageRes = R.drawable.action1,
                movieType = MovieType.POPULAR
            ),
            Movie(
                id = 2,
                titleRes = R.string.title3,
                descriptionRes = R.string.description3,
                categoryRes = R.string.category3,
                imageRes = R.drawable.sciencefic1,
                movieType = MovieType.POPULAR
            ),
            Movie(
                id = 3,
                titleRes = R.string.title4,
                descriptionRes = R.string.description4,
                categoryRes = R.string.description4,
                imageRes = R.drawable.horror1,
                movieType = MovieType.POPULAR
            )
        )
    }
}