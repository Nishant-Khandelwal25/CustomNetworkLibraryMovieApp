package com.example.customnetworkapimovie.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha.medium
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.rememberAsyncImagePainter
import com.example.customnetworkapimovie.R
import com.example.customnetworkapimovie.model.data.MoviesResultData
import com.example.customnetworkapimovie.navigation.Screen
import com.example.customnetworkapimovie.ui.theme.LARGE_PADDING
import com.example.customnetworkapimovie.ui.theme.MEDIUM_PADDING
import com.example.customnetworkapimovie.ui.theme.Movie_ITEM_HEIGHT
import com.example.customnetworkapimovie.ui.theme.SMALL_PADDING
import com.example.customnetworkapimovie.ui.theme.topAppBarContentColor
import com.example.customnetworkapimovie.util.IMAGES_URL

@Composable
fun MoviesListContent(movies: LazyPagingItems<MoviesResultData>, navController: NavHostController) {
    val result = handlePagingResult(movies = movies)

    if (result) {
        LazyColumn(
            contentPadding = PaddingValues(SMALL_PADDING),
            verticalArrangement = spacedBy(SMALL_PADDING),
        ) {
            items(
                key = movies.itemKey { it.id }, count = movies.itemCount
            ) {
                val movie = movies[it]
                movie?.let { item ->
                    MovieItem(movie = item, navController)
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(movies: LazyPagingItems<MoviesResultData>): Boolean {
    movies.apply {
        val error = when {
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }

        return when {
            loadState.refresh is LoadState.Loading -> {
                ShimmerEffect()
                false
            }

            error != null -> {
                EmptyScreen(error = error)
                false
            }

            movies.itemCount < 1 -> {
                EmptyScreen()
                false
            }

            else -> true
        }
    }
}

@Composable
fun MovieItem(
    movie: MoviesResultData,
    navController: NavHostController
) {

    val painter = rememberAsyncImagePainter("${IMAGES_URL}${movie.posterPath}",
        placeholder = painterResource(id = R.drawable.placeholder),
        onError = {
            R.drawable.placeholder
        })

    Box(
        modifier = Modifier
            .height(Movie_ITEM_HEIGHT)
            .clickable {
                navController.navigate(Screen.DetailsScreen.passMovieId(movie.id))
            }, contentAlignment = Alignment.BottomStart
    ) {
        Surface(shape = RoundedCornerShape(LARGE_PADDING)) {
            Image(
                painter = painter,
                contentDescription = "Movie Poster",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Surface(
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .fillMaxWidth(),
            color = Color.Black.copy(alpha = medium),
            shape = RoundedCornerShape(bottomEnd = 0.dp, bottomStart = 0.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(MEDIUM_PADDING)
            ) {
                Text(
                    text = movie.title,
                    color = topAppBarContentColor,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = movie.overview,
                    color = Color.White.copy(alpha = medium),
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = SMALL_PADDING)
                )

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = SMALL_PADDING),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Release Date: ${movie.releaseDate}",
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        maxLines = 1,
                        color = topAppBarContentColor,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "Rating: ${String.format("%.2f", movie.voteAverage)}/10",
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        maxLines = 1,
                        color = topAppBarContentColor,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }

    }
}