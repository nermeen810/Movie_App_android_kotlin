package com.nermeen.movie_app.data.dataSource.remoteDataSource

import com.nermeen.movie_app.data.model.CategoryResponse
import com.nermeen.movie_app.data.model.DetailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("3/movie/{path}")
    suspend fun getCategory(@Path("path") path:String,@Query("api_key") api_key:String) : Response<CategoryResponse?>

    @GET("3/movie/{path}")
    suspend fun loadMoreMovies(@Path("path") path:String,@Query("api_key") api_key:String, @Query("page") page:Int) : Response<CategoryResponse?>

    @GET("3/movie/{path}")
    suspend fun getMoviesDetails(@Path("path") path: Long,@Query("api_key") api_key:String) :Response<DetailsResponse?>
}