package kgarel.isis.fr

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
fun ShowDetailsPage(sizeClasses: WindowSizeClass, viewModel: MainViewModel) {

    val show by viewModel.selectedShow.collectAsStateWithLifecycle()
    val image_url_prefix = "https://image.tmdb.org/t/p/"
    val profile_size = "h632"
    val backdrop_size = "w1280"

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(20.dp, 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                text = "Title: ${show?.name ?: ""}",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = MaterialTheme.typography.headlineLarge.fontWeight
            )
            Text(
                text = "\nOriginal title (${show?.original_language ?: ""}) : ${show?.original_name ?: ""}",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = MaterialTheme.typography.headlineSmall.fontWeight
            )
        }

        item {
            AsyncImage(
                model = image_url_prefix + backdrop_size + show?.backdrop_path,
                contentDescription = show?.id.toString(),
            )
        }

        item {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = image_url_prefix + profile_size + show?.poster_path,
                    contentDescription = show?.id.toString(),
                    modifier = Modifier.padding(10.dp)
                )
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    Text("${show?.first_air_date ?: ""} - ${show?.last_air_date ?: ""}")
                    Text("Genres: ")
                    show?.genres?.forEach {
                        Text(it.name)
                    }
                }
            }
        }

        item {
            Text(show?.overview ?: "")
        }

        item {
            Text("Rating: ${show?.vote_average ?: ""} ")
        }

        item {
            Text("Cast: ")

            show?.credits?.cast?.take(10)?.forEach {
                AsyncImage(
                    model = image_url_prefix + profile_size + it.profile_path,
                    contentDescription = it.id.toString(),
                )
                Text(it.name)
            }
        }
    }


}
