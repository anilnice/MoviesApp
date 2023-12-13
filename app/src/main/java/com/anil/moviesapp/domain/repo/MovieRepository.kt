package com.anil.di


import com.anil.moviesapp.data.model.Movie
import com.anil.moviesapp.data.model.toMovie
import com.anil.moviesapp.domain.di.remote.source.MovieService
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