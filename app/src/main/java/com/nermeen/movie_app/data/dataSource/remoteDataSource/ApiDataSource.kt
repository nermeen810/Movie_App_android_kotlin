package com.nermeen.movie_app.data.dataSource.remoteDataSource

import com.nermeen.movie_app.utils.Constants
import javax.inject.Inject

class ApiDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getCategory(category:String) = apiService.getCategory(category,Constants.api_key)
    suspend fun loadMoreMoviesForCategory(category:String,page: Int) = apiService.loadMoreMovies(category,Constants.api_key,page)
    suspend fun getMovieDetails(id: Long) = apiService.getMoviesDetails(id,Constants.api_key)

}