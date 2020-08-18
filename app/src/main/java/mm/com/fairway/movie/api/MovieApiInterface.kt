package mm.com.fairway.movie.api

import mm.com.fairway.movie.model.Movie
import mm.com.fairway.movie.model.detailMovie.MovieID
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiInterface {
    @GET("popular")
    fun getMovieList(
        @Query("api_key")api_key:String,
        @Query("language")language:String,
        @Query("page")page:Int

    ):Call<Movie>

    @GET("{movie_id}")
    fun getDetail(
        @Path("movie_id") movie_id:Int,
        @Query("api_key")api_key:String,
        @Query("language")language:String

    ): Call<MovieID>
}