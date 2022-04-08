package com.nermeen.movie_app.data.resposatory

import com.nermeen.movie_app.data.model.CategoryResponse
import com.nermeen.movie_app.data.model.DetailsResponse
import com.nermeen.movie_app.utils.Result

interface RemoteRepo {
    suspend fun getCategory(category:String) : Result<CategoryResponse?>
    suspend fun loadMoreMoviesForCategory(category:String, page:Int) : Result<CategoryResponse?>
    suspend fun getMovieDetails(id:Long) :Result<DetailsResponse?>
}