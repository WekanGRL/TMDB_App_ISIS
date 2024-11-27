package kgarel.isis.fr

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import coil.compose.AsyncImage

@Composable
fun MoviesPage(sizeClasses: WindowSizeClass, viewModel: MainViewModel, onMovieClick: () -> Unit) {
    val movies by viewModel.movies.collectAsStateWithLifecycle()
    val image_url_prefix = "https://image.tmdb.org/t/p/w780"

    if (movies.isEmpty()) {
        viewModel.getInitialMovies()
    }

    val widthClass = sizeClasses.windowWidthSizeClass
    val columnsNumber = when (widthClass) {
        WindowWidthSizeClass.EXPANDED -> 4
        else -> 2
    }

    Column(modifier = Modifier.fillMaxSize()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(columnsNumber),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            items(movies.size) { index ->
                Card(
                    onClick = {
                        viewModel.getMovieDetails(movies[index].id)
                        onMovieClick()
                    },
                    modifier = Modifier.fillMaxSize(),
                ) {
                    AsyncImage(
                        model = image_url_prefix + movies[index].poster_path,
                        contentDescription = movies[index].id.toString(),
                    )
                    Text(
                        text = movies[index].title,
                    )
                    Text(movies[index].release_date)

                    IconButton(
                        onClick = {viewModel.onMovieFavClick(movies[index].id)},
                    ){
                        if(movies[index].isFav){
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
}