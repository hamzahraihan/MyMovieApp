package com.example.mymovieapp.data

enum class MovieType(val title: String) {
    NOW_PLAYING(title = "Now Playing"),
    POPULAR(title = "Popular"),
    TOP_RATED(title = "Top Rated"),
    UPCOMING(title = "Upcoming")
}