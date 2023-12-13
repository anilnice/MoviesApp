package com.anil.moviesapp.features.screen.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.anil.moviesapp.data.model.Movie
import com.anil.moviesapp.util.Constants.Companion.IMAGE_URL
import com.anil.moviesapp.util.Utility.toJson

@Composable
fun HomeScreenPopular(navController: NavHostController, homeViewModel: HomeViewModel) {
    //val homeViewModel = viewModel(modelClass = HomeViewModel::class.java)
    val popular_movies by homeViewModel.popular_movie.collectAsState()
    Log.d("TAG", "HomeScreenPopular: ${popular_movies.size}")
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        items(popular_movies) { item: Movie ->
            MovieCard(movie = item, navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieCard(movie: Movie, navController: NavHostController) {
    val image = rememberAsyncImagePainter(model = IMAGE_URL + movie.poster_path)
    Card(
        elevation = CardDefaults.cardElevation(5.dp),
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth(),
        onClick = {
            val movies = movie.toJson()
            navController.navigate("Details?movies=${movies}")
        }
    ) {
        Box(
            modifier = Modifier,
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alpha = 1f,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )
        }
    }
}


