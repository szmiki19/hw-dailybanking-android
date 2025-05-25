package com.mbh.moviebrowser

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mbh.moviebrowser.core.util.Constants.LANGUAGE_CODE_EN_US
import com.mbh.moviebrowser.core.util.Constants.TIME_WINDOW_WEEK
import com.mbh.moviebrowser.data.repository.MovieBrowserRepository
import com.mbh.moviebrowser.features.movieDetails.MovieDetailsScreen
import com.mbh.moviebrowser.features.movieDetails.MovieDetailsViewModel
import com.mbh.moviebrowser.features.movieList.MovieListScreen
import com.mbh.moviebrowser.features.movieList.MovieListViewModel
import com.mbh.moviebrowser.data.store.MovieStore
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var movieBrowserRepository: MovieBrowserRepository

    @Inject
    lateinit var coroutineScope: CoroutineScope

    @SuppressLint("ViewModelConstructorInComposable")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val movieStore = MovieStore(
            movieBrowserRepository = movieBrowserRepository,
            coroutineScope = coroutineScope,
            timeWindow = TIME_WINDOW_WEEK,
            language = LANGUAGE_CODE_EN_US,
        )

        setContent {
            MaterialTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "list") {
                    composable("list") {
                        MovieListScreen(
                            viewModel = MovieListViewModel(movieStore),
                            onDetailsClicked = {
                                navController.navigate("details")
                            },
                        )
                    }
                    composable("details") {
                        MovieDetailsScreen(
                            viewModel = MovieDetailsViewModel(movieStore),
                        )
                    }
                }
            }
        }
    }
}
