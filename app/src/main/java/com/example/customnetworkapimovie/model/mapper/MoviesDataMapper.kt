package com.example.customnetworkapimovie.model.mapper

import com.example.customnetworkapimovie.model.data.MoviesResultData
import com.example.customnetworkapimovie.model.response.MoviesResult
import com.example.customnetworkapimovie.util.safeMap

fun List<MoviesResult>.toMoviesResultData(): List<MoviesResultData> {
    return safeMap {
        MoviesResultData(
            adult = it.adult ?: false,
            backdropPath = it.backdropPath.orEmpty(),
            genreIds = it.genreIds ?: emptyList(),
            id = it.id ?: 0,
            originalLanguage = it.originalLanguage.orEmpty(),
            originalTitle = it.originalTitle.orEmpty(),
            overview = it.overview.orEmpty(),
            popularity = it.popularity ?: 0.0,
            posterPath = it.posterPath.orEmpty(),
            releaseDate = it.releaseDate.orEmpty(),
            title = it.title.orEmpty(),
            video = it.video ?: false,
            voteAverage = it.voteAverage ?: 0.0,
            voteCount = it.voteCount ?: 0
        )
    }
}