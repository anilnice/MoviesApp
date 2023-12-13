package com.anil.moviesapp.features.screen.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anil.moviesapp.data.model.Actors
import com.anil.moviesapp.data.model.Reviews
import com.anil.moviesapp.domain.usecase.GetMoviesCastReviewUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewmodel @Inject constructor(private val getMoviesCastReviewUseCase: GetMoviesCastReviewUseCase) :
    ViewModel() {
    private val _movie_actors = MutableStateFlow(emptyList<Actors>())
    val movie_actors: StateFlow<List<Actors>> get() = _movie_actors

    private val _movies_reviews = MutableStateFlow(emptyList<Reviews>())
    val movie_reviews: StateFlow<List<Reviews>> get() = _movies_reviews

    init {
        getMoviesCasts()
        getMovieReviews()
    }

    private var movieId: String? = null

    fun setMovieId(id: String) {
        movieId = id
        // Now you can use the movieId in your ViewModel as needed
        // For example, you can trigger some data loading based on this id.
        getMoviesCasts()
        getMovieReviews()
    }

    private fun getMoviesCasts() {
        viewModelScope.launch {
            try {
                val moviecast = getMoviesCastReviewUseCase.getMovieActors(movie_id = movieId!!)
                _movie_actors.value = moviecast
            } catch (e: Exception) {
                Log.d("TAG", "getMoviesCast: ${e.message}")
            }
        }
    }

    private fun getMovieReviews() {
        viewModelScope.launch {
            try {
                val moviereviews = getMoviesCastReviewUseCase.getMovieReviews(movie_id = movieId!!)
                _movies_reviews.value = moviereviews
            } catch (e: Exception) {
                Log.d("TAG", "getMovieReviews: ${e.message}")
            }
        }
    }
}