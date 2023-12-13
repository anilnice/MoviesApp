package com.anil.moviesapp.domain.usecase

import com.anil.moviesapp.data.model.Actors
import com.anil.moviesapp.data.model.Reviews
import com.anil.moviesapp.domain.repo.MovieActorRepository
import javax.inject.Inject

class GetMoviesCastReviewUseCase @Inject constructor(private val movieActorRepository: MovieActorRepository) {

    suspend fun getMovieActors(movie_id: String): List<Actors> {
        return movieActorRepository.getMovieCasts(movie_id = movie_id)
    }

    suspend fun getMovieReviews(movie_id: String): List<Reviews> {
        return movieActorRepository.getMovieReviews(movie_id = movie_id)
    }
}