package mm.com.fairway.movie.api

import mm.com.fairway.movie.model.Movie
import mm.com.fairway.movie.model.detailMovie.MovieID
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    private val BASE_URL = "https://api.themoviedb.org/3/movie/"

    companion object {
        val api_Key = "76a2481b184330a7695211869897c6af"
    }

    var apiInterface: MovieApiInterface

    init {
        var retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiInterface = retrofit.create(MovieApiInterface::class.java)
    }

    fun getMovieList(
        api_Key: String,
        language: String,
        page: Int
    ): Call<Movie> {
        return apiInterface.getMovieList(api_Key, language, page)
    }

    fun getDetail(
        movie_id: Int,
        api_Key: String,
        language: String):Call<MovieID> {// to call fun from detailMovie
        return apiInterface.getDetail(movie_id, api_Key, language)

    }
}

//https://api.themoviedb.org/3/movie/612706?api_key=76a2481b184330a7695211869897c6af&language=en-US
//https://api.themoviedb.org/3/movie/popular?api_key=<<api_key>>&language=en-US&page=1