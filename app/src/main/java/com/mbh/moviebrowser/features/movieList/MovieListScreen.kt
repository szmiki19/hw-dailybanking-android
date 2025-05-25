package com.mbh.moviebrowser.features.movieList

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.mbh.moviebrowser.R
import com.mbh.moviebrowser.data.repository.model.MovieData

@Composable
fun MovieListScreen(viewModel: MovieListViewModel, onDetailsClicked: (MovieData) -> Unit) {
    MovieListScreenUI(
        viewModel.movies.collectAsState().value,
        viewModel.isSuccess.collectAsState().value,
        viewModel.isLoading.collectAsState().value,
    ) {
        viewModel.storeMovieForNavigation(it)
        onDetailsClicked(it)
    }
}

@Composable
fun MovieListScreenUI(
    movies: List<MovieData>,
    isSuccess: Boolean,
    isLoading: Boolean,
    onDetailsClicked: (MovieData) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(movies) { item ->
            MovieListItem(
                movie = item,
                onDetailsClicked,
            )
        }
    }

    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
            content = {
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            }
        )
    }

    if (!isSuccess) {
        Toast.makeText(
            LocalContext.current,
            stringResource(R.string.error_message),
            Toast.LENGTH_LONG
        ).show()
    }
}

@Composable
private fun MovieListItem(
    movie: MovieData,
    onDetailsClicked: (MovieData) -> Unit,
) {
    Row(
        Modifier.padding(horizontal = 16.dp, vertical = 8.dp).clickable {
            onDetailsClicked(movie)
        },
    ) {
        Box {
            AsyncImage(
                model = movie.coverUrl,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.width(80.dp).zIndex(1.0f),
            )
            val image = if (movie.isFavorite) {
                painterResource(id = android.R.drawable.btn_star_big_on)
            } else {
                painterResource(id = android.R.drawable.btn_star_big_off)
            }
            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier.padding(all = 4.dp).zIndex(2.0f).align(Alignment.TopEnd),
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = movie.title,
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Black,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = movie.genres,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black,
            )
            Spacer(modifier = Modifier.height(8.dp))
            LinearProgressIndicator(
                progress = { movie.rating / 10.0f },
                drawStopIndicator = { },
                modifier = Modifier.fillMaxWidth(),
                gapSize = 0.dp,
            )
        }
    }
}

@Composable
@Preview(
    name = "phone",
    Devices.PHONE,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
fun MovieListScreenUIPreview() {
    MovieListScreenUI(
        listOf(
            MovieData(
                id = 455476,
                title = "Knights of the Zodiac",
                genres = "Action, Sci-fi",
                overview = "When a headstrong street orphan, Seiya, in search of his abducted sister unwittingly taps into hidden powers, he discovers he might be the only person alive who can protect a reincarnated goddess, sent to watch over humanity. Can he let his past go and embrace his destiny to become a Knight of the Zodiac?",
                coverUrl = "https://image.tmdb.org/t/p/w500/qW4crfED8mpNDadSmMdi7ZDzhXF.jpg",
                releaseDate = "2025-05-25",
                rating = 6.5f,
                isFavorite = true,
            ),
            MovieData(
                id = 385687,
                title = "Fast X",
                genres = "Action",
                overview = "Over many missions and against impossible odds, Dom Toretto and his family have outsmarted, out-nerved and outdriven every foe in their path. Now, they confront the most lethal opponent they've ever faced: A terrifying threat emerging from the shadows of the past who's fueled by blood revenge, and who is determined to shatter this family and destroy everything—and everyone—that Dom loves, forever.",
                coverUrl = "https://image.tmdb.org/t/p/w500/fiVW06jE7z9YnO4trhaMEdclSiC.jpg",
                releaseDate = "2025-05-25",
                rating = 7.4f,
                isFavorite = false,
            ),
        ),
        onDetailsClicked = {},
        isSuccess = true,
        isLoading = false
    )
}
