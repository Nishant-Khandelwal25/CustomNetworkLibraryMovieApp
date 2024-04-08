package com.example.customnetworkapimovie.repository

import androidx.paging.PagingData
import com.example.customnetworkapimovie.util.Result
import com.example.customnetworkapimovie.model.data.MovieDetailsData
import com.example.customnetworkapimovie.model.data.MoviesResultData
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun getLatestMoviesData(): Flow<PagingData<MoviesResultData>>
    fun getPopularMoviesData(): Flow<PagingData<MoviesResultData>>
    fun getMovieDetailsData(movieId: Int): Flow<Result<MovieDetailsData>>
}