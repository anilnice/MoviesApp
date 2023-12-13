package com.anil.moviesapp.domain.di.remote.source

import com.anil.moviesapp.data.movies.Result
import com.anil.moviesapp.domain.di.remote.api.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieService @Inject constructor(private val apiClient: ApiClient) {

    suspend fun getPopularMovies(): List<Result> {
        return withContext(Dispatchers.IO) {
            val movies = apiClient.getPopularMovies()
            movies.body()!!.results
        }
    }

    suspend fun getTopRatedMovies(): List<Result> {
        return withContext(Dispatchers.IO) {
            val movies = apiClient.getTopRatedMovies()
            movies.body()!!.results
        }
    }
}