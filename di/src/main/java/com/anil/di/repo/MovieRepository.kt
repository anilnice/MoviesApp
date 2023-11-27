package com.anil.di


import com.anil.di.domain.item.Movie
import com.anil.di.domain.item.toMovie
import com.anil.di.remote.MovieService
import javax.inject.Inject

class MovieRepository @Inject constructor(private val movieService: MovieService) {

    suspend fun getPopularMovies(): List<Movie> {
        return movieService.getPopularMovies().map {
            it.toMovie()
        }
    }

    suspend fun getTopRatedMovies(): List<Movie> {
        return movieService.getTopRatedMovies().map {
            it.toMovie()
        }
    }
}