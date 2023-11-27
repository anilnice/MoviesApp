package com.anil.di.domain

import com.anil.di.MovieRepository
import com.anil.di.domain.item.Movie
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    suspend fun getPopular(): List<Movie> {
        return movieRepository.getPopularMovies()
    }

    suspend fun getTopRated(): List<Movie> {
        return movieRepository.getTopRatedMovies()
    }
}