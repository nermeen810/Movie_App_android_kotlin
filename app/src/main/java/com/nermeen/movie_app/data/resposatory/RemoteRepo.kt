package com.nermeen.movie_app.data.resposatory

import com.nermeen.movie_app.data.model.MoviesResponse
import com.nermeen.movie_app.data.model.DetailsResponse
import com.nermeen.movie_app.utils.Result

interface RemoteRepo {
    suspend fun loadMoreMovies(page:Int) : Result<MoviesResponse?>
    suspend fun getMovieDetails(id:Long) :Result<DetailsResponse?>
}