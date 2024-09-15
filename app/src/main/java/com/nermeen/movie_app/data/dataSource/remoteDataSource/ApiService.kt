package com.nermeen.movie_app.data.dataSource.remoteDataSource

import com.nermeen.movie_app.data.model.MoviesResponse
import com.nermeen.movie_app.data.model.DetailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("popular")
    suspend fun loadMoreMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int,
    ): Response<MoviesResponse?>

    @GET("{id}")
    suspend fun getMoviesDetails(@Path("id") id: Long,@Query("api_key") apiKey:String) :Response<DetailsResponse?>
}