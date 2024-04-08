package com.example.customnetworkapimovie.screens.details

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.customnetworkapimovie.R
import com.example.customnetworkapimovie.components.AppBar
import com.example.customnetworkapimovie.model.data.MovieDetailsData
import com.example.customnetworkapimovie.ui.theme.NETWORK_ERROR_ICON_HEIGHT
import com.example.customnetworkapimovie.ui.theme.SMALL_PADDING
import com.example.customnetworkapimovie.ui.theme.DarkGray
import com.example.customnetworkapimovie.ui.theme.LightGray
import com.example.customnetworkapimovie.ui.theme.topAppBarBackgroundColor
import com.example.customnetworkapimovie.util.IMAGES_URL

@Composable
fun DetailsScreen(
    navController: NavHostController, viewModel: DetailsScreenViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    Scaffold(topBar = {
        AppBar(title = "Details Screen", icon = Icons.Default.ArrowBack) {
            navController.popBackStack()
        }
    }) {
        DetailView(
            movieDetailsData = uiState.value.movieDetailsData,
            modifier = Modifier.padding(it),
            uiState.value.error
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailView(movieDetailsData: MovieDetailsData?, modifier: Modifier, error: Exception?) {
    if (movieDetailsData != null) {
        val painter = rememberAsyncImagePainter("$IMAGES_URL${movieDetailsData.posterPath}",
            placeholder = painterResource(id = R.drawable.placeholder),
            onError = {
                R.drawable.placeholder
            })
        Column(
            Modifier
                .fillMaxSize()
                .padding(SMALL_PADDING)
                .verticalScroll(rememberScrollState())
        ) {
            Image(
                painter = painter,
                contentDescription = "Movie Poster",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )

            Spacer(modifier = Modifier.height(SMALL_PADDING))

            Text(
                text = movieDetailsData.title,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Spacer(modifier = Modifier.height(SMALL_PADDING))

            Text(text = "Overview")

            Text(
                text = movieDetailsData.overview,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            )

            Spacer(modifier = Modifier.height(SMALL_PADDING))

            Row(
                Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Release Date: ${movieDetailsData.releaseDate}",
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = "Rating: ${String.format("%.2f", movieDetailsData.voteAverage)}/10",
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(modifier = Modifier.padding(SMALL_PADDING))
            Row(
                modifier = Modifier.horizontalScroll(rememberScrollState()),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = "Genres: ")
                repeat(movieDetailsData.genres.size) {
                    Chip(
                        onClick = {},
                        enabled = false,
                        border = BorderStroke(
                            ChipDefaults.OutlinedBorderSize,
                            topAppBarBackgroundColor
                        ),
                        colors = ChipDefaults.chipColors(
                            backgroundColor = Color.White,
                            contentColor = Color.Gray
                        ),
                        modifier = Modifier
                            .padding(horizontal = SMALL_PADDING)
                    ) {
                        Text(text = movieDetailsData.genres[it].name, color = Color.Gray)
                    }
                }
            }
        }
    } else {
        var startAnimation by remember {
            mutableStateOf(false)
        }

        val alphaAnim by animateFloatAsState(
            targetValue = if (startAnimation) ContentAlpha.disabled else 0f,
            label = "",
            animationSpec = tween(1000)
        )

        LaunchedEffect(key1 = true) {
            startAnimation = true
        }
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.network_error),
                contentDescription = "Error icon",
                tint = if (isSystemInDarkTheme()) LightGray else DarkGray,
                modifier = Modifier
                    .size(NETWORK_ERROR_ICON_HEIGHT)
                    .alpha(alphaAnim)
            )
            Text(
                text = error?.message.orEmpty(),
                modifier = Modifier
                    .padding(top = SMALL_PADDING)
                    .alpha(alphaAnim),
                color = if (isSystemInDarkTheme()) LightGray else DarkGray,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                fontSize = MaterialTheme.typography.titleSmall.fontSize
            )
        }
    }
}