package com.example.customnetworkapimovie.screens.popular_movies_screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.customnetworkapimovie.components.MoviesListContent

@Composable
fun PopularMoviesScreen(
    navController: NavHostController,
    viewModel: PopularMoviesViewModel = hiltViewModel()
) {

    val latestMovies = viewModel.pagingData.collectAsLazyPagingItems()

    MoviesListContent(movies = latestMovies , navController)
}