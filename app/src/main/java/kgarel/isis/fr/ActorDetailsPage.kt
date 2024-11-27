package kgarel.isis.fr

import android.util.Log
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
fun ActorDetailsPage(sizeClasses: WindowSizeClass, viewModel: MainViewModel) {

    val actor by viewModel.selectedActor.collectAsStateWithLifecycle()
    val image_url_prefix = "https://image.tmdb.org/t/p/w780"

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(20.dp, 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                text = "Name: ${actor?.name ?: ""}",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = MaterialTheme.typography.headlineLarge.fontWeight
            )
            Text(
                text = "\nBorn - ${actor?.place_of_birth ?: ""} : \n ${actor?.birthday ?: ""} - ${actor?.deathday ?: ""}",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = MaterialTheme.typography.headlineSmall.fontWeight
            )
        }

        item {
            AsyncImage(
                model = image_url_prefix + actor?.profile_path,
                contentDescription = actor?.id.toString(),
                modifier = Modifier.padding(5.dp)
            )
        }

        item {
            Text(actor?.biography ?: "")
        }

        item {
            Text("Popularity: ${actor?.popularity?: ""}")
        }

        item {
            if (actor?.adult == true) {
                Text("☣️☣️☣️☣️☣️")
            }
        }
    }


}
