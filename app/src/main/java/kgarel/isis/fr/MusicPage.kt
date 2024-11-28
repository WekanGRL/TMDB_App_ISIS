package kgarel.isis.fr

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import coil.compose.AsyncImage

@Composable
fun MusicPage(sizeClasses: WindowSizeClass, viewModel: MainViewModel) {
    val baseImageURL = "file:///android_asset/images/"
    val playlist by viewModel.playlist.collectAsStateWithLifecycle()

    val widthClass = sizeClasses.windowWidthSizeClass
    val columnsNumber = when (widthClass) {
        WindowWidthSizeClass.EXPANDED -> 3
        else -> 2
    }

    // Je comprends plus rien aux layouts, tant pis


    Row {
        AsyncImage(
            model = baseImageURL + playlist?.md5_image,
            contentDescription = "playlist image",
            modifier = Modifier.clip(shape = CircleShape).fillMaxSize()
        )
        Text(text = playlist?.title ?: "")
    }
    Text("Créée par ${playlist?.creator?.name ?: ""}")
    LazyVerticalGrid(
        columns = GridCells.Fixed(columnsNumber),
        modifier = Modifier
            .padding(20.dp, 5.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(6) { index ->
            Card(
                modifier = Modifier.fillMaxSize(),
            ) {
                AsyncImage(
                    model = baseImageURL + (playlist?.tracks?.data?.get(index)?.album?.cover
                        ?: ""),
                    contentDescription = (playlist?.tracks?.data?.get(index)?.id.toString()
                        ?: ""),
                    modifier = Modifier
                        .size(50.dp)
                        .clip(shape = CircleShape),
                    alignment = Alignment.Center
                )
                Text(
                    text = (playlist?.tracks?.data?.get(index)?.title ?: ""),
                )
            }
        }
    }
}



