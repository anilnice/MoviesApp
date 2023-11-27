package com.anil.moviesapp.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anil.di.domain.GetMovieUseCase
import com.anil.di.domain.item.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getMovieUseCase: GetMovieUseCase) :
    ViewModel() {

    private val _popular_movie = MutableStateFlow(emptyList<Movie>())
    val popular_movie: StateFlow<List<Movie>> get() = _popular_movie

    private val _toprated_movies = MutableStateFlow(emptyList<Movie>())
    val top_rated: StateFlow<List<Movie>> get() = _toprated_movies

    init {
        getPopularMovies()
        getTopRatedMovies()
    }

    private fun getPopularMovies() {
        viewModelScope.launch {
            try {
                val movies = getMovieUseCase.getPopular()
                _popular_movie.value = movies
            } catch (ex: Exception) {
                Log.d("TAG-", "getPopularMovies: ${ex.message}")
            }
        }
    }

    private fun getTopRatedMovies() {
        viewModelScope.launch {
            try {
                val movies = getMovieUseCase.getTopRated()
                _toprated_movies.value = movies
            } catch (ex: Exception) {

            }
        }
    }
}