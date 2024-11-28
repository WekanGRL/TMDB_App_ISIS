package kgarel.isis.fr

data class Actor(
    val adult: Boolean = false,
    val biography: String = "",
    val birthday: String = "",
    val deathday: String? = null,
    val id: Int = 0,
    val name: String = "",
    val place_of_birth: String = "",
    val popularity: Double = 0.0,
    val profile_path: String = "",

    val isFav : Boolean = false
)

class ListActors(
    var page: Int = 0,
    var results: List<Actor> = listOf(),
)

class Genre(
    val id: Int = 0,
    val name: String = ""
)

class Credits(
    val cast: List<Actor> = listOf(),
    val crew: List<Actor> = listOf(),
)

data class Movie(
    val adult: Boolean = false,
    val backdrop_path: String = "",
    val budget: Int = 0,
    val genres: List<Genre> = listOf(),
    val id: Int = 0,
    val origin_country: List<String> = listOf(),
    val original_language: String = "",
    val original_title: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val poster_path: String = "",
    val release_date: String = "",
    val revenue: Int = 0,
    val runtime: Int = 0,
    val status: String = "",
    val tagline: String = "",
    val title: String = "",
    val video: Boolean = false,
    val vote_average: Double = 0.0,
    val vote_count: Int = 0,
    val credits : Credits = Credits(),
    val isFav : Boolean = false
)

class ListMovies(
    var page: Int = 0,
    var results: List<Movie> = listOf(),
)

data class Show(                                                                                                                                                     Show (
    val adult: Boolean = false,
    val backdrop_path: String = "",
    val episode_run_time: List<Int> = listOf(),
    val first_air_date: String = "",
    val genres: List<Genre> = listOf(),
    val homepage: String = "",
    val id: Int = 0,
    val in_production: Boolean = false,
    val languages: List<String> = listOf(),
    val last_air_date: String = "",
    val name: String = "",
    val number_of_episodes: Int = 0,
    val number_of_seasons: Int = 0,
    val origin_country: List<String> = listOf(),
    val original_language: String = "",
    val original_name: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val poster_path: String = "",
    val status: String = "",
    val tagline: String = "",
    val type: String = "",
    val vote_average: Double = 0.0,
    val vote_count: Int = 0,
    val credits: Credits? = Credits(),

    val isFav : Boolean = false
)

class ListShows (
    var page: Int = 0,
    var results: List<Show> = listOf(),
)

data class Playlist(
    val checksum: String,
    val collaborative: Boolean,
    val cover: String,
    val creation_date: String,
    val creator: Creator,
    val description: String,
    val duration: Int,
    val fans: Int,
    val id: Int,
    val is_loved_track: Boolean,
    val link: String,
    val md5_image: String,
    val nb_tracks: Int,
    val picture_type: String,
    val `public`: Boolean,
    val share: String,
    val title: String,
    val tracklist: String,
    val tracks: Tracks,
    val type: String
)

data class Creator(
    val id: Int,
    val name: String,
    val tracklist: String,
    val type: String
)

data class Tracks(
    val checksum: String,
    val `data`: List<Data>
)

data class Data(
    val album: Album,
    val artist: Artist,
    val duration: Int,
    val explicit_content_cover: Int,
    val explicit_content_lyrics: Int,
    val explicit_lyrics: Boolean,
    val id: Long,
    val isrc: String,
    val link: String,
    val md5_image: String,
    val preview: String,
    val rank: Int,
    val readable: Boolean,
    val time_add: Int,
    val title: String,
    val title_short: String,
    val title_version: String,
    val type: String
)

data class Album(
    val cover: String,
    val id: Int,
    val md5_image: String,
    val title: String,
    val tracklist: String,
    val type: String,
    val upc: String
)

data class Artist(
    val id: Int,
    val link: String,
    val name: String,
    val tracklist: String,
    val type: String
)

