package com.example.customnetworkapimovie.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.customnetworkapimovie.util.Result
import com.example.customnetworkapimovie.model.data.MovieDetailsData
import com.example.customnetworkapimovie.model.data.MoviesResultData
import com.example.customnetworkapimovie.model.mapper.toMovieDetailsResponseData
import com.example.customnetworkapimovie.network.ApiService
import com.example.customnetworkapimovie.paging.GetLatestMovieListPagingSource
import com.example.customnetworkapimovie.paging.GetPopularMovieListPagingSource
import com.example.customnetworkapimovie.util.NetworkHelper
import com.example.customnetworkapimovie.util.PAGE_LIMIT
import com.example.customnetworkapimovie.util.PREFETCH_DISTANCE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import java.net.ConnectException
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class MainRepositoryImpl @Inject constructor(
    private val networkHelper: NetworkHelper
) : MainRepository {
    override fun getLatestMoviesData(): Flow<PagingData<MoviesResultData>> {
        return Pager(
            PagingConfig(
                pageSize = PAGE_LIMIT,
                initialLoadSize = PAGE_LIMIT,
                prefetchDistance = PREFETCH_DISTANCE
            )
        ) {
            GetLatestMovieListPagingSource(networkHelper)
        }.flow
    }

    override fun getPopularMoviesData(): Flow<PagingData<MoviesResultData>> {
        return Pager(
            PagingConfig(
                pageSize = PAGE_LIMIT,
                initialLoadSize = PAGE_LIMIT,
                prefetchDistance = PREFETCH_DISTANCE
            )
        ) {
            GetPopularMovieListPagingSource(networkHelper)
        }.flow
    }

    override fun getMovieDetailsData(movieId: Int): Flow<Result<MovieDetailsData>> {
        return flow {
            if (networkHelper.isOnline()) {
                lateinit var movieData: MovieDetailsData
                var success = false
                var error = ""
                flow {
                    val response = suspendCoroutine { continuation ->
                        ApiService.getMovieDetailsApiResponse(movieId) {
                            when (it) {
                                is Result.Success -> {
                                    it.data.let { movieDetailsResponse ->
                                        movieData =
                                            movieDetailsResponse.toMovieDetailsResponseData()
                                    }
                                    success = true
                                }

                                is Result.Error -> {
                                    success = false
                                    error = it.exception?.message.orEmpty()
                                }
                            }
                            continuation.resume(it)
                        }
                    }
                    emit(response)
                }.collect {
                    if (success)
                        emit(Result.Success(movieData))
                    else
                        emit(Result.Error(Exception(error)))
                }
            } else {
                emit(Result.Error(ConnectException("Internet Unavailable")))
            }
        }
    }
}