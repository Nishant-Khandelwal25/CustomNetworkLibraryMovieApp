package com.example.customnetworkapimovie.screens.latest_movies_screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.customnetworkapimovie.components.MoviesListContent

@Composable
fun LatestMoviesScreen(
    navController: NavHostController,
    viewModel: LatestMoviesViewModel = hiltViewModel(),
) {

    val latestMovies = viewModel.pagingData.collectAsLazyPagingItems()
    MoviesListContent(movies = latestMovies, navController)
}