package kgarel.isis.fr

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import coil.compose.AsyncImage

@Composable
fun ActorsPage(sizeClasses: WindowSizeClass, viewModel: MainViewModel, onActorClick: () -> Unit){
    val actors by viewModel.actors.collectAsStateWithLifecycle()
    val image_url_prefix = "https://image.tmdb.org/t/p/w780"

    if (actors.isEmpty()) {
        viewModel.getInitialActors()
    }

    val widthClass = sizeClasses.windowWidthSizeClass
    val columnsNumber = when (widthClass) {
        WindowWidthSizeClass.EXPANDED -> 4
        else -> 2
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(columnsNumber),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier.padding(5.dp)
    ) {
        items(actors.size) { index ->
            Card(
                onClick = {
                    viewModel.getActorDetails(actors[index].id)
                    onActorClick()
                },
                modifier = Modifier.fillMaxSize(),
            ) {
                AsyncImage(
                    model = image_url_prefix + actors[index].profile_path,
                    contentDescription = actors[index].id.toString(),
                )
                Text(actors[index].name)
                Text(actors[index].birthday)
                IconButton(
                    onClick = {viewModel.onActorFavClick(actors[index].id)},
                ){
                    if(actors[index].isFav){
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "FAV Filled",
                            Modifier.size(30.dp)
                        )
                    }
                    else {
                        Icon(
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = "FAV Empty",
                            Modifier.size(30.dp)
                        )
                    }
                }
            }
        }
    }

}