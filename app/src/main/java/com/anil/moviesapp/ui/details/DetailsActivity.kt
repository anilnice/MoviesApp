package com.anil.moviesapp.ui.details

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.anil.di.util.Constants
import com.anil.di.util.PreferencesManager
import com.anil.moviesapp.MoviesApplication
import com.anil.moviesapp.R
import com.anil.moviesapp.ui.actorsCard
import com.anil.moviesapp.ui.reviewCard
import com.anil.moviesapp.ui.theme.MoviesAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier,
                    color = MaterialTheme.colorScheme.background
                ) {
                    val movie = PreferencesManager(MoviesApplication.appContext).getMoviesData()
                    val myViewModel = viewModel(modelClass = DetailViewmodel::class.java)
                    myViewModel.setMovieId(movie.id.toString())
                    val actors = myViewModel.movie_actors.collectAsState()
                    val reviews = myViewModel.movie_reviews.collectAsState()

                    val image =
                        rememberAsyncImagePainter(model = Constants.IMAGE_URL + movie.poster_path)
                    val background_image =
                        rememberAsyncImagePainter(model = Constants.IMAGE_URL + movie.backdrop_path)
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .verticalScroll(rememberScrollState())
                        ) {
                            Text(
                                text = movie.title,
                                modifier = Modifier,
                                style = MaterialTheme.typography.displaySmall.copy(

                                )
                            )
                            Box(modifier = Modifier) {
                                Image(
                                    painter = background_image,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(300.dp),
                                    contentScale = ContentScale.FillBounds,
                                    alpha = 0.4f
                                )
                                Image(
                                    painter = image,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .align(Alignment.Center)
                                        .clip(RoundedCornerShape(4.dp)),
                                    contentDescription = null
                                )
                            }
                            Text(text = movie.overview)
                            Text(
                                text = getString(R.string.cast),
                                modifier = Modifier.padding(8.dp),
                                style = MaterialTheme.typography.headlineMedium
                            )
                            LazyRow(
                                Modifier
                                    .fillMaxWidth()
                            ) {
                                items(actors.value) { actor ->
                                    actorsCard(actor = actor)
                                }
                            }
                            Text(
                                text = getString(R.string.reviews),
                                modifier = Modifier
                                    .padding(8.dp),
                                style = MaterialTheme.typography.headlineMedium
                            )
                            LazyColumn(
                                contentPadding = PaddingValues(4.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(400.dp),
                                state = rememberLazyListState()
                            ) {
                                items(reviews.value) { review ->
                                    reviewCard(reviews = review)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}