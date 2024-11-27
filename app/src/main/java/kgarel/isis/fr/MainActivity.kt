package kgarel.isis.fr

import android.app.Application
import android.content.Context
import android.media.Image
import android.os.Bundle
import android.telecom.Call.Details
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarColors
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlinx.serialization.Serializable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import androidx.window.core.layout.WindowSizeClass
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kgarel.isis.fr.ui.theme.PROFILTheme
import javax.inject.Singleton

@Serializable
class Profile

@Serializable
class Movies

@Serializable
class Shows

@Serializable
class Actors

@Serializable
class MovieDetails

@Serializable
class ShowDetails

@Serializable
class ActorDetails

@HiltAndroidApp
class IMDBApplication : Application()

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: MainViewModel by viewModels()
        setContent {
            PROFILTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass

                Scaffold(containerColor = Color.Black, contentColor = Color.White, topBar = {
                    if (navBackStackEntry?.destination?.hasRoute<Movies>() == true || navBackStackEntry?.destination?.hasRoute<Shows>() == true || navBackStackEntry?.destination?.hasRoute<Actors>() == true) {
                        Row {
                            SearchBar(
                                query = viewModel.searchText,
                                onQueryChange = { viewModel.searchText = it },
                                onSearch = { viewModel.onSearch() },
                                active = false,
                                onActiveChange = {},
                                modifier = Modifier.fillMaxWidth(0.8f),
                                leadingIcon = {
                                    IconButton(onClick = { viewModel.onSearch() }) {
                                        Icon(
                                            Icons.Filled.Search,
                                            contentDescription = "Search",
                                            tint = colorResource(id = R.color.teal_700)
                                        )
                                    }
                                },
                                placeholder = { Text("Search") },
//                            colors = SearchBarColors(
//                                containerColor = Color.LightGray,
//                                dividerColor = Color.Transparent,
//                                inputFieldColors=
//                                    textColor = Color.White,
//                                    placeholderColor = Color.White
//                                )
//                            )
                            ) { }
                            IconButton(
                                onClick = { viewModel.onGlobalFavClick() },
                                modifier = Modifier.fillMaxWidth().padding(5.dp),
                            ) {
                                if (viewModel.globalFav) {
                                    Icon(
                                        imageVector = Icons.Default.Favorite,
                                        contentDescription = "FAV Filled",
                                        Modifier.size(50.dp)
                                    )
                                } else {
                                    Icon(
                                        imageVector = Icons.Default.FavoriteBorder,
                                        contentDescription = "FAV Empty",
                                        Modifier.size(50.dp)
                                    )
                                }
                            }
                        }
                    }
                }, bottomBar = {
                    if (navBackStackEntry?.destination?.hasRoute<Profile>() == false) {
                        AppNavBar(windowSizeClass, navController)
                    }
                }) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Profile(),
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable<Profile> {
                            viewModel.changeDestination(Destination.PROFILE)
                            ProfilePage(
                                windowSizeClass,
                                onStartClick = { navController.navigate(Movies()) })
                        }
                        composable<Movies> {
                            viewModel.changeDestination(Destination.MOVIES)
                            MoviesPage(windowSizeClass, viewModel, onMovieClick = {
                                navController.navigate(MovieDetails())
                            })
                        }
                        composable<Shows> {
                            viewModel.changeDestination(Destination.SHOWS)
                            ShowsPage(windowSizeClass, viewModel, onShowClick = {
                                navController.navigate(ShowDetails())
                            })
                        }
                        composable<Actors> {
                            viewModel.changeDestination(Destination.ACTORS)
                            ActorsPage(windowSizeClass, viewModel, onActorClick = {
                                navController.navigate(ActorDetails())
                            })
                        }
                        composable<MovieDetails> {
                            viewModel.changeDestination(Destination.MOVIE_DETAILS)
                            MovieDetailsPage(windowSizeClass, viewModel)
                        }
                        composable<ShowDetails> {
                            viewModel.changeDestination(Destination.SHOW_DETAILS)
                            ShowDetailsPage(windowSizeClass, viewModel)
                        }
                        composable<ActorDetails> {
                            viewModel.changeDestination(Destination.ACTOR_DETAILS)
                            ActorDetailsPage(windowSizeClass, viewModel)
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun AppNavBar(sizeClasses: WindowSizeClass, navController: NavHostController) {
    NavigationBar(containerColor = Color.Magenta, contentColor = Color.White) {
        NavigationBarItem(icon = {
            Icon(
                painterResource(R.drawable.movie_24px), contentDescription = "Movies"
            )
        },
            label = { Text("Movies") },
            selected = navController.currentBackStackEntry?.destination?.hasRoute<Movies>() == true,
            onClick = { navController.navigate(Movies()) })
        NavigationBarItem(icon = {
            Icon(
                painterResource(R.drawable.computer_24px), contentDescription = "Shows"
            )
        },
            label = { Text("Shows") },
            selected = navController.currentBackStackEntry?.destination?.hasRoute<Shows>() == true,
            onClick = { navController.navigate(Shows()) })
        NavigationBarItem(icon = {
            Icon(
                painterResource(R.drawable.face_24px), contentDescription = "Actors"
            )
        },
            label = { Text("Actors") },
            selected = navController.currentBackStackEntry?.destination?.hasRoute<Actors>() == true,
            onClick = { navController.navigate(Actors()) })
    }
}

