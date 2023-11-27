package com.anil.di.domain

import com.anil.di.domain.item.Actors
import com.anil.di.domain.item.Reviews
import com.anil.di.repo.MovieActorRepository
import javax.inject.Inject

class GetMoviesCastReviewUseCase @Inject constructor(private val movieActorRepository: MovieActorRepository) {

    suspend fun getMovieActors(movie_id: String): List<Actors> {
        return movieActorRepository.getMovieCasts(movie_id = movie_id)
    }

    suspend fun getMovieReviews(movie_id: String): List<Reviews> {
        return movieActorRepository.getMovieReviews(movie_id = movie_id)
    }
}