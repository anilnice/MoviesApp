package com.anil.moviesapp.features.screen.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.anil.moviesapp.R
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
    val image_background = rememberAsyncImagePainter(model = IMAGE_URL + movie.backdrop_path)
    Card(
        elevation = CardDefaults.cardElevation(5.dp),
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth(),
        onClick = {
            /*PreferencesManager(MoviesApplication.appContext).saveMoviesData(movie)
            MoviesApplication.appContext.startActivity(
                Intent(
                    MoviesApplication.appContext,
                    DetailsActivity::class.java
                )
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            )*/
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

@Composable
fun MoviesBottomNavigation(
    modifier: Modifier = Modifier
) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        var selectedItem by remember { mutableIntStateOf(0) }
        val items =
            listOf(stringResource(id = R.string.popular), stringResource(id = R.string.top_rated))

        NavigationBar {
            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Favorite, contentDescription = item) },
                    label = { Text(item) },
                    selected = selectedItem == index,
                    onClick = { selectedItem = index }
                )
            }
        }
    }

}

@Composable
fun MoviesLandscapRailNavigation(modifier: Modifier = Modifier) {
    NavigationRail(
        modifier = modifier.padding(start = 8.dp, end = 8.dp),
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var selectedItem by rememberSaveable { mutableIntStateOf(0) }
            val items = listOf("Home", "Settings")
            val icons = listOf(Icons.Filled.Home, Icons.Filled.Settings)
            NavigationRail {
                items.forEachIndexed { index, item ->
                    NavigationRailItem(
                        icon = { Icon(icons[index], contentDescription = item) },
                        label = { Text(item) },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index }
                    )
                }
            }
        }

    }
}

@Composable
fun MoviesLandscapeApp() {
    Surface(color = MaterialTheme.colorScheme.background) {
        Row(Modifier.fillMaxHeight()) {
            MoviesLandscapRailNavigation()
            //HomeScreenPopular(Modifier, navController)
        }
    }
}

@Composable
fun MoviesPortraitApp(navController: NavHostController, viewModel: HomeViewModel) {
    /* Scaffold(
         //topBar = { TopBar() },
         //bottomBar = {MoviesBottomNavigation(modifier = Modifier)}
     ) { padding ->
         Box(Modifier.fillMaxSize()) {
             HomeScreenPopular(Modifier.padding(padding))
         }
     }*/
    HomeScreenPopular(navController, viewModel)
}

@Composable
fun MoviesHomeScreen(navController: NavHostController, viewModel: HomeViewModel) {
    MoviesPortraitApp(navController, viewModel)
    /*when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            MoviesPortraitApp()
        }

        WindowWidthSizeClass.Expanded -> {
            MoviesLandscapeApp()
        }
    }*/
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.moviesapp), fontSize = 18.sp) },
    )
}


