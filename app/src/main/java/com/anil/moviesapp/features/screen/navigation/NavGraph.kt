package com.anil.moviesapp.features.screen.navigation

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.anil.moviesapp.features.screen.details.DetailScreen
import com.anil.moviesapp.features.screen.details.DetailViewmodel
import com.anil.moviesapp.features.screen.home.HomeViewModel
import com.anil.moviesapp.features.screen.home.MoviesHomeScreen

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun Nav() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home") {
        composable(route = "Home") {
            //val windowSizeClass = calculateWindowSizeClass(activity = MoviesApplication.appContext)
            //val navController = rememberNavController()
            val viewModel = hiltViewModel<HomeViewModel>()
            MoviesHomeScreen(navController, viewModel)
        }
        composable(
            route = "Details?movies={movies}",
            arguments = listOf(
                navArgument(name = "movies") {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) { backstackEntry ->
            val viewModel = hiltViewModel<DetailViewmodel>()
            DetailScreen(
                navController,
                movies = backstackEntry.arguments?.getString("movies"),
                viewModel
            )
        }
    }
}