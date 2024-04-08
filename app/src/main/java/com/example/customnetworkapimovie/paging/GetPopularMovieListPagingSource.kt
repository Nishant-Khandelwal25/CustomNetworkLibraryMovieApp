package com.example.customnetworkapimovie.paging

import androidx.paging.LoadState
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.customnetworkapimovie.util.Result
import com.example.customnetworkapimovie.model.data.MoviesResultData
import com.example.customnetworkapimovie.model.mapper.toMoviesResultData
import com.example.customnetworkapimovie.network.ApiService
import com.example.customnetworkapimovie.util.NetworkHelper
import com.example.customnetworkapimovie.util.PAGE_INCREMENT_COUNTER
import com.example.customnetworkapimovie.util.PAGE_START_COUNT
import java.net.ConnectException
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class GetPopularMovieListPagingSource @Inject constructor(
    private val networkHelper: NetworkHelper,
) : PagingSource<Int, MoviesResultData>() {
    override fun getRefreshKey(state: PagingState<Int, MoviesResultData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.minus(PAGE_INCREMENT_COUNTER) ?: anchorPage?.nextKey?.plus(
                PAGE_INCREMENT_COUNTER
            )
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesResultData> {
        return if (networkHelper.isOnline()) {
            try {
                val pageNumber = params.key ?: PAGE_START_COUNT

                val response = suspendCoroutine { continuation ->
                    ApiService.getPopularMoviesApiResponse(pageNumber) {
                        continuation.resume(it)
                    }
                }

                var latestMoviesData = mutableListOf<MoviesResultData>()

                when (response) {
                    is Result.Success -> {
                        latestMoviesData =
                            response.data.results?.toMoviesResultData() as MutableList<MoviesResultData>
                    }

                    is Result.Error -> {
                        LoadState.Error(response.exception ?: Exception("Unknown error"))
                    }
                }
                val widgetCount = latestMoviesData.size

                LoadResult.Page(
                    data = latestMoviesData.distinct().toList(),
                    prevKey = null,
                    nextKey = if (widgetCount == 0 || widgetCount < params.loadSize) null
                    else pageNumber.plus(PAGE_INCREMENT_COUNTER)
                )
            } catch (e: Exception) {
                LoadResult.Error(e)
            }
        } else {
            LoadResult.Error(ConnectException("No Network Available"))
        }
    }
}