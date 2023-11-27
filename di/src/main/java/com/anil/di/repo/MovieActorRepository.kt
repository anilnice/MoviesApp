package com.anil.di.repo

import com.anil.di.domain.item.Actors
import com.anil.di.domain.item.Reviews
import com.anil.di.domain.item.toActors
import com.anil.di.domain.item.toReviews
import com.anil.di.remote.CastReviewService
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