package com.example.customnetworkapimovie.navigation

import com.example.customnetworkapimovie.util.DETAILS_ARGUMENT_KEY

sealed class Screen(val route: String) {
    data object MainScreen : Screen("main_screen")
    data object DetailsScreen : Screen("details_screen/{$DETAILS_ARGUMENT_KEY}") {
        fun passMovieId(movieId: Int): String {
            return "details_screen/$movieId"
        }
    }
}