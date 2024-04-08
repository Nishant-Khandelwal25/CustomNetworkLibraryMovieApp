package com.example.customnetworkapimovie.screens.details

import com.example.customnetworkapimovie.model.data.MovieDetailsData

data class MovieDetailsUiState(
    val movieDetailsData: MovieDetailsData? = null,
    val error: Exception? = Exception("Something went wrong")
)