package com.example.customnetworkapimovie.network

import com.example.custom_networking.CustomNetwork
import com.example.custom_networking.JsonObjectListener
import com.example.customnetworkapimovie.BuildConfig
import com.example.customnetworkapimovie.model.response.MovieDetailsResponse
import com.example.customnetworkapimovie.model.response.MoviesResponse
import com.example.customnetworkapimovie.util.AUTHORIZATION_HEADER_KEY
import com.example.customnetworkapimovie.util.BASE_URL
import com.example.customnetworkapimovie.util.Result
import com.google.gson.Gson
import org.json.JSONObject

class ApiService {
    companion object {
        fun getLatestMoviesApiResponse(
            pageNumber: Int,
            result: (Result<MoviesResponse>) -> Unit
        ) {
            val map = HashMap<String, String>()
            map[AUTHORIZATION_HEADER_KEY] = BuildConfig.AUTHORIZATION_HEADER
            CustomNetwork.HttpRequest(CustomNetwork.GET)
                .url("$BASE_URL/now_playing?page=$pageNumber")
                .header(map)
                .makeRequest(object : JsonObjectListener {
                    override fun onResponse(response: JSONObject?) {
                        val movieResponse =
                            Gson().fromJson(response.toString(), MoviesResponse::class.java)
                        result(Result.Success(movieResponse))
                    }

                    override fun onFailure(exception: Exception?) {
                        result(Result.Error(exception))

                    }
                })
        }

        fun getPopularMoviesApiResponse(
            pageNumber: Int,
            result: (Result<MoviesResponse>) -> Unit
        ) {
            val map = HashMap<String, String>()
            map[AUTHORIZATION_HEADER_KEY] = BuildConfig.AUTHORIZATION_HEADER
            CustomNetwork.HttpRequest(CustomNetwork.GET)
                .url("$BASE_URL/popular?page=$pageNumber")
                .header(map)
                .makeRequest(object : JsonObjectListener {
                    override fun onResponse(response: JSONObject?) {
                        val movieResponse =
                            Gson().fromJson(response.toString(), MoviesResponse::class.java)
                        result(Result.Success(movieResponse))
                    }

                    override fun onFailure(exception: Exception?) {
                        result(Result.Error(exception))

                    }
                })
        }

        fun getMovieDetailsApiResponse(
            movieId: Int,
            result: (Result<MovieDetailsResponse>) -> Unit
        ) {
            val map = HashMap<String, String>()
            map[AUTHORIZATION_HEADER_KEY] = BuildConfig.AUTHORIZATION_HEADER
            CustomNetwork.HttpRequest(CustomNetwork.GET)
                .url("$BASE_URL/$movieId")
                .header(map)
                .makeRequest(object : JsonObjectListener {
                    override fun onResponse(response: JSONObject?) {
                        val movieDetailsResponse =
                            Gson().fromJson(response.toString(), MovieDetailsResponse::class.java)
                        result(Result.Success(movieDetailsResponse))
                    }

                    override fun onFailure(exception: Exception?) {
                        result(Result.Error(exception))

                    }
                })
        }
    }
}