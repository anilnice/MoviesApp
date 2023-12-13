package com.anil.moviesapp.domain.di.remote.source

import com.anil.moviesapp.data.actors.Cast
import com.anil.moviesapp.data.reviews.Result
import com.anil.moviesapp.domain.di.remote.api.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CastReviewService @Inject constructor(private val apiClient: ApiClient) {

    suspend fun getMovieCredits(movie_id: String): List<Cast> {
        return withContext(Dispatchers.IO) {
            val actors = apiClient.getMovieCredits(movie_id = movie_id)
            actors.body()!!.cast
        }
    }

    suspend fun getMovieReviews(movie_id: String): List<Result> {
        return withContext(Dispatchers.IO) {
            val actors = apiClient.getMovieReviews(movie_id = movie_id)
            actors.body()!!.results
        }
    }

}