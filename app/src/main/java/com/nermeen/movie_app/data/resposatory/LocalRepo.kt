package com.nermeen.movie_app.data.resposatory

import com.nermeen.movie_app.data.model.Movies

interface LocalRepo {
    suspend fun getMovies(): List<Movies>
    suspend fun insertMovie(movie: Movies)
    suspend fun isAddedToFavorite(movieId: Long): Boolean
    suspend fun deleteMovieById(movieId: Long)
}