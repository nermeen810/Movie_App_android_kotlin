package com.nermeen.movie_app.data.dataSource.remoteDataSource

import com.nermeen.movie_app.utils.Constants
import javax.inject.Inject

class ApiDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun loadMoreMovies(page: Int) = apiService.loadMoreMovies(Constants.API_KEY, Constants.LANGUAGE, page)
    suspend fun getMovieDetails(id: Long) = apiService.getMoviesDetails(id,Constants.API_KEY)
}