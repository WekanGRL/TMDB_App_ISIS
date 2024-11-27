package kgarel.isis.fr

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.window.core.layout.WindowSizeClass
import coil.compose.AsyncImage

@Composable
fun MovieDetailsPage(sizeClasses: WindowSizeClass, viewModel: MainViewModel) {

    val movie by viewModel.selectedMovie.collectAsStateWithLifecycle()
    val image_url_prefix = "https://image.tmdb.org/t/p/"
    val poster_size = "w500"
    val backdrop_size = "w1280"

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp, 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                text = "Title: ${movie?.title ?: ""}",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = MaterialTheme.typography.headlineLarge.fontWeight
            )
            Text(
                text = "\nOriginal title (${movie?.original_language ?: ""}) : ${movie?.original_title ?: ""}",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = MaterialTheme.typography.headlineSmall.fontWeight
            )
        }

        item {
            AsyncImage(
                model = image_url_prefix + backdrop_size + movie?.backdrop_path,
                contentDescription = movie?.id.toString(),
            )
        }

        item {
            Row(modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = image_url_prefix + poster_size + movie?.poster_path,
                    contentDescription = movie?.id.toString(),
                    modifier = Modifier.padding(5.dp)
                )
                Column (
                    modifier = Modifier.fillMaxSize().padding(5.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(movie?.release_date ?: "")
                    Text("Genres: ")
                    movie?.genres?.forEach {
                        Text(it.name)
                    }
                }
            }

        }

        item {
            Text(movie?.overview ?: "")
        }

        item {
            Text("Rating: ${movie?.vote_average ?: ""}")
        }

        item {
            Text("Cast: ")
            movie?.credits?.cast?.take(10)?.forEach {
                AsyncImage(
                    model = image_url_prefix + poster_size + it.profile_path,
                    contentDescription = it.id.toString(),
                )
                Text(it.name)
            }

        }

        item {
            if (movie?.adult == true) {
                Text("☣️☣️☣️☣️☣️")
            }
        }
    }


}
