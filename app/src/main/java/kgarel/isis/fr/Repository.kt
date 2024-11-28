package kgarel.isis.fr

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

class Repository(val tmdbapi: TMDBApi, val db: TMDBDao) {

    val api_key = "741e74ad116edeaf99ff07418b68a085"

    // MOVIES
    suspend fun trendingMovies(): List<Movie> {

        val listApi = tmdbapi.getTrendingMovies(api_key).results
        val listDB = db.getMovies()

        return listApi.map { movie ->
            if (listDB.any { it.id == movie.id }) {
                movie.copy(isFav = true)
            } else {
                movie
            }
        }
    }

    suspend fun searchMovies(search: String) = tmdbapi.searchMoviesByName(api_key, search)
    suspend fun getMovie(movie_id: Int) = tmdbapi.getMovieById(movie_id, api_key, "credits")

    suspend fun getFavoriteMovies(): List<Movie> {
        return db.getMovies().map { movie ->
            movie.file.copy(isFav = true)
        }
    }
    suspend fun addFavoriteMovie(movie: Movie) = db.insertMovie(MovieEntity(movie.id, movie))
    suspend fun removeFavoriteMovie(movie_id: Int) = db.deleteMovie(movie_id)

    // SHOWS
    suspend fun trendingShows(): List<Show> {
        val listApi = tmdbapi.getTrendingShows(api_key).results
        val listDB = db.getShows()

        return listApi.map { show ->
            if (listDB.any { it.id == show.id }) {
                show.copy(isFav = true)
            } else {
                show
            }
        }
    }

    suspend fun searchShows(search: String) = tmdbapi.searchShowsByName(api_key, search)
    suspend fun getShow(show_id: Int) = tmdbapi.getShowById(show_id, api_key, "credits")

    suspend fun getFavoriteShows(): List<Show> {
        return db.getShows().map { show ->
            show.file.copy(isFav = true)
        }
    }
    suspend fun addFavoriteShow(show: Show) = db.insertShow(ShowEntity(show.id, show))
    suspend fun removeFavoriteShow(show_id: Int) = db.deleteShow(show_id)

    // ACTORS
    suspend fun trendingActors(): List<Actor> {
        val listApi = tmdbapi.getTrendingActors(api_key).results
        val listDB = db.getActors()

        return listApi.map { actor ->
            if (listDB.any { it.id == actor.id }) {
                actor.copy(isFav = true)
            } else {
                actor
            }
        }
    }


    suspend fun searchActors(search: String) = tmdbapi.searchActorsByName(api_key, search)
    suspend fun getActor(actor_id: Int) = tmdbapi.getActorById(actor_id, api_key)

    suspend fun getFavoriteActors(): List<Actor> {
        return db.getActors().map { actor ->
            actor.file.copy(isFav = true)
        }
    }
    suspend fun addFavoriteActor(actor: Actor) = db.insertActor(ActorEntity(actor.id, actor))
    suspend fun removeFavoriteActor(actor_id: Int) = db.deleteActor(actor_id)
}

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideTmdbApi(): TMDBApi = Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create()).build().create(TMDBApi::class.java)

    @Singleton
    @Provides
    fun provideRepository(api: TMDBApi, db: TMDBDao) = Repository(api, db)

    @Singleton
    @Provides
    fun providerConverters(): Converters {
        val moshi = Moshi.Builder().build()
        return Converters(moshi)
    }

    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context, converters: Converters): TMDBDao {
        return Room.databaseBuilder(
            context, AppDatabase::class.java, "database-name"
        ).addTypeConverter(converters).fallbackToDestructiveMigration().build().dao()
    }

}
