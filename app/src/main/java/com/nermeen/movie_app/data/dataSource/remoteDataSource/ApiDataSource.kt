package com.nermeen.movie_app.data.dataSource.remoteDataSource

import com.nermeen.movie_app.utils.Constants
import javax.inject.Inject

class ApiDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getCategory() = apiService.getCategory(Constants.api_key)
    suspend fun loadMoreMoviesForCategory(categoryId:String,page: Int) = apiService.loadMoreMovies(Constants.api_key,categoryId,page)
    suspend fun getMovieDetails(id: Long) = apiService.getMoviesDetails(id,Constants.api_key)

}