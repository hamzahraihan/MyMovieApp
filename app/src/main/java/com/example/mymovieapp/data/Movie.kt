package com.example.mymovieapp.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Movie(
    val id: Long,
    @StringRes val titleRes: Int,
    @StringRes val descriptionRes: Int,
    @StringRes val categoryRes: Int,
    @DrawableRes val imageRes: Int
)