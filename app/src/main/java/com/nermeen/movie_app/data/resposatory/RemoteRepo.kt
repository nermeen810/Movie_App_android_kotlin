package com.nermeen.movie_app.data.resposatory

import com.nermeen.movie_app.data.model.CategoryResponse
import com.nermeen.movie_app.data.model.MoviesResponse
import com.nermeen.movie_app.data.model.DetailsResponse
import com.nermeen.movie_app.utils.Result

interface RemoteRepo {
    suspend fun getCategory() : Result<CategoryResponse?>
    suspend fun loadMoreMoviesForCategory(categoryId:String, page:Int) : Result<MoviesResponse?>
    suspend fun getMovieDetails(id:Long) :Result<DetailsResponse?>
}