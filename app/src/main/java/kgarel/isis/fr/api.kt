package kgarel.isis.fr

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBApi {

    //MOVIES
    @GET("trending/movie/week")
    suspend fun getTrendingMovies(@Query("api_key") api_key: String): ListMovies

    @GET("movie/{movie_id}")
    suspend fun getMovieById(@Path("movie_id") movie_id: Int, @Query("api_key") api_key: String, @Query ("append_to_response") append_to_response: String): Movie
    //TODO: append_to_response=credits

    @GET("search/movie")
    suspend fun searchMoviesByName(@Query("api_key") api_key: String, @Query("query") query: String): ListMovies

    //SHOWS
    @GET("trending/tv/week")
    suspend fun getTrendingShows(@Query("api_key") api_key: String): ListShows

    @GET("tv/{tv_id}")
    suspend fun getShowById(@Path("tv_id") tv_id: Int, @Query("api_key") api_key: String, @Query ("append_to_response") append_to_response: String): Show

    @GET("search/tv")
    suspend fun searchShowsByName(@Query("api_key") api_key: String, @Query("query") query: String): ListShows


    //ACTORS
    @GET("trending/person/week")
    suspend fun getTrendingActors(@Query("api_key") api_key: String): ListActors

    @GET("person/{person_id}")
    suspend fun getActorById(@Path("person_id") person_id: Int, @Query("api_key") api_key: String): Actor

    @GET("search/person")
    suspend fun searchActorsByName(@Query("api_key") api_key: String, @Query("query") query: String): ListActors
}