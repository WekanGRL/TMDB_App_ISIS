package kgarel.isis.fr

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.memory.MemoryCache
import com.example.myapplicationtest.playlistjson
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

enum class Destination (index: Int) {
    PROFILE(0),
    MOVIES(1),
    SHOWS(2),
    ACTORS(3),
    MOVIE_DETAILS(4),
    SHOW_DETAILS(5),
    ACTOR_DETAILS(6),
    MUSIC(99)
}

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: Repository) : ViewModel() {
    val destination = MutableStateFlow(Destination.MOVIES)

    val movies = MutableStateFlow<List<Movie>>(listOf())
    val shows = MutableStateFlow<List<Show>>(listOf())
    val actors = MutableStateFlow<List<Actor>>(listOf())

    val playlist=MutableStateFlow<Playlist?>(null)

    val selectedMovie = MutableStateFlow<Movie?>(null)
    val selectedShow = MutableStateFlow<Show?>(null)
    val selectedActor = MutableStateFlow<Actor?>(null)

    var globalFav by mutableStateOf(false)
    var searchText by mutableStateOf("")

    init {
        getInitialMovies()
        getInitialShows()
        getInitialActors()
    }

    fun getPlaylist(){
        playlist.value = fetchPlaylist()
    }

    // récupère la playlist
    fun fetchPlaylist(): Playlist {
        val moshi=Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        return moshi.adapter(Playlist::class.java).fromJson(playlistjson)!!
    }




    fun changeDestination(newDestination: Destination) {
        destination.value = newDestination
    }

    fun onSearch() {
        when (destination.value) {
            Destination.MOVIES -> searchMovies(searchText)
            Destination.SHOWS -> searchSeries(searchText)
            Destination.ACTORS -> searchActors(searchText)
            else -> {}
        }
    }

    fun onGlobalFavClick(){
        globalFav = !globalFav
        if(globalFav){
            when (destination.value) {
                Destination.MOVIES -> getFavoriteMovies()
                Destination.SHOWS -> getFavoriteShows()
                Destination.ACTORS -> getFavoriteActors()
                else -> {}
            }
        }
        else {
            when (destination.value) {
                Destination.MOVIES -> getInitialMovies()
                Destination.SHOWS -> getInitialShows()
                Destination.ACTORS -> getInitialActors()
                else -> {}
            }
        }
    }

    // MOVIES
    fun getInitialMovies() {
        // Only take from db otherwise app crashes
        viewModelScope.launch {
            movies.value =
            //repo.trendingMovies()
            repo.getFavoriteMovies()
        }
    }

    private fun searchMovies(search: String) {
        viewModelScope.launch { movies.value = repo.searchMovies(search).results }
    }

    fun getMovieDetails(movie_id: Int) {
        viewModelScope.launch { selectedMovie.value = repo.getMovie(movie_id) }
    }

    fun addFavoriteMovie(movie: Movie) {
        viewModelScope.launch { repo.addFavoriteMovie(movie) }
    }

    fun removeFavoriteMovie(movie_id: Int) {
        viewModelScope.launch { repo.removeFavoriteMovie(movie_id) }
    }

    fun getFavoriteMovies() {
        viewModelScope.launch { movies.value = repo.getFavoriteMovies() }
    }

    fun onMovieFavClick(movie_id: Int) {
        val movie = movies.value.find { it.id == movie_id } ?: return
        if (movie.isFav) {
            removeFavoriteMovie(movie.id)
        } else {
            addFavoriteMovie(movie)
        }
        getInitialMovies()
    }

    // SHOWS
    fun getInitialShows() {
        viewModelScope.launch {
            // Only take from db otherwise app crashes
            shows.value =
              //  repo.trendingShows()
            repo.getFavoriteShows()
        }
    }

    private fun searchSeries(search: String) {
        viewModelScope.launch { shows.value = repo.searchShows(search).results }
    }

    fun getShowDetails(show_id: Int) {
        viewModelScope.launch { selectedShow.value = repo.getShow(show_id) }
    }

    fun addFavoriteShow(show: Show) {
        viewModelScope.launch { repo.addFavoriteShow(show) }
    }

    fun removeFavoriteShow(show_id: Int) {
        viewModelScope.launch { repo.removeFavoriteShow(show_id) }
    }

    fun getFavoriteShows() {
        viewModelScope.launch { shows.value = repo.getFavoriteShows() }
    }

    fun onShowFavClick(show_id: Int) {
        val show = shows.value.find { it.id == show_id } ?: return
        if (show.isFav) {
            removeFavoriteShow(show.id)
        } else {
            addFavoriteShow(show)
        }
        getInitialShows()
    }

    // ACTORS
    fun getInitialActors() {
        // Only take from db otherwise app crashes
        viewModelScope.launch {
            actors.value =
            repo.getFavoriteActors()
           // repo.trendingActors()
        }
    }

    private fun searchActors(search: String) {
        viewModelScope.launch { actors.value = repo.searchActors(search).results }
    }

    fun getActorDetails(actor_id: Int) {
        viewModelScope.launch { selectedActor.value = repo.getActor(actor_id) }
    }

    fun addFavoriteActor(actor: Actor) {
        viewModelScope.launch { repo.addFavoriteActor(actor) }
    }

    fun removeFavoriteActor(actor_id: Int) {
        viewModelScope.launch { repo.removeFavoriteActor(actor_id) }
    }

    fun getFavoriteActors() {
        viewModelScope.launch { actors.value = repo.getFavoriteActors() }
    }

    fun onActorFavClick(actor_id: Int) {
        val actor = actors.value.find { it.id == actor_id } ?: return
        if (actor.isFav) {
            removeFavoriteActor(actor.id)
        } else {
            addFavoriteActor(actor)
        }
        getInitialActors()
    }
}
