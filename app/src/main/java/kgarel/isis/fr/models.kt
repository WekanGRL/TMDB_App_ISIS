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

data class                                                                                                                                                      Show (
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