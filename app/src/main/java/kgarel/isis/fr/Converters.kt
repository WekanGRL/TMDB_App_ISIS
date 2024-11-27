package kgarel.isis.fr

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.squareup.moshi.Moshi

@ProvidedTypeConverter
class Converters(moshi: Moshi) {
    val movieJsonAdapter = moshi.adapter(Movie::class.java)
    val showJsonAdapter = moshi.adapter(Show::class.java)
    val actorJsonAdapter = moshi.adapter(Actor::class.java)

    @TypeConverter
    fun StringToTmdbMovie(value: String): Movie? {
        return movieJsonAdapter.fromJson(value)
    }

    @TypeConverter
    fun TmdbMovieToString(movie: Movie): String {
        return movieJsonAdapter.toJson(movie)
    }

    @TypeConverter
    fun StringToTmdbShow(value: String): Show? {
        return showJsonAdapter.fromJson(value)
    }

    @TypeConverter
    fun TmdbShowToString(film: Show): String {
        return showJsonAdapter.toJson(film)
    }

    @TypeConverter
    fun StringToTmdbActor(value: String): Actor? {
        return actorJsonAdapter.fromJson(value)
    }

    @TypeConverter
    fun TmdbActorToString(film: Actor): String {
        return actorJsonAdapter.toJson(film)
    }
}