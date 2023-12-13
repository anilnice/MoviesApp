package com.anil.moviesapp.domain.repo

import com.anil.moviesapp.data.model.Actors
import com.anil.moviesapp.data.model.Reviews
import com.anil.moviesapp.data.model.toActors
import com.anil.moviesapp.data.model.toReviews
import com.anil.moviesapp.domain.di.remote.source.CastReviewService
import javax.inject.Inject

class MovieActorRepository @Inject constructor(private val castReviewService: CastReviewService) {

    suspend fun getMovieCasts(movie_id: String): List<Actors> {
        return castReviewService.getMovieCredits(movie_id).map {
            it.toActors()
        }
    }

    suspend fun getMovieReviews(movie_id: String): List<Reviews> {
        return castReviewService.getMovieReviews(movie_id).map {
            it.toReviews()
        }
    }
}