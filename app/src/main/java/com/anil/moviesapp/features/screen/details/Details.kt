package com.anil.moviesapp.features.screen.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.anil.moviesapp.R
import com.anil.moviesapp.data.model.Movie
import com.anil.moviesapp.features.components.actorsCard
import com.anil.moviesapp.features.components.reviewCard
import com.anil.moviesapp.util.Constants
import com.anil.moviesapp.util.Utility.fromJson

@Composable
fun DetailScreen(navController: NavHostController, movies: String?, myViewModel: DetailViewmodel) {

    val movie = movies?.fromJson<Movie>()
    myViewModel.setMovieId(movie!!.id.toString())

    val actors = myViewModel.movie_actors.collectAsState()
    val reviews = myViewModel.movie_reviews.collectAsState()

    val image =
        rememberAsyncImagePainter(model = Constants.IMAGE_URL + movie.poster_path)
    val background_image =
        rememberAsyncImagePainter(model = Constants.IMAGE_URL + movie.backdrop_path)
    Box(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp)
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
                    fontWeight = FontWeight.Bold
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
                        .width(200.dp)
                        .height(250.dp)
                        .align(Alignment.Center)
                        .clip(RoundedCornerShape(4.dp)),
                    contentDescription = null
                )
            }

            Text(
                text = stringResource(id = R.string.overview),
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.headlineMedium
            )
            Text(text = movie.overview)
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.release_date),
                    modifier = Modifier,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = movie.release_date,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.rating),
                    modifier = Modifier,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = movie.vote_average.toString(),
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
            Text(
                text = stringResource(id = R.string.cast),
                modifier = Modifier.padding(start = 8.dp, bottom = 16.dp),
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
                text = stringResource(id = R.string.reviews),
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