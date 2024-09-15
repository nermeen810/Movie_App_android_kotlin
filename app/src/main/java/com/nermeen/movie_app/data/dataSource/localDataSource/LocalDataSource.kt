package com.nermeen.movie_app.data.dataSource.localDataSource

import com.nermeen.movie_app.data.model.Movies
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val dao: MoviesDao) {

    fun getMovies(): List<Movies> {
        return dao.getMovies()
    }

    fun insertMovie(movie: Movies) {
        dao.insertMovie(movie)
    }

    fun isAddedToFavorite(movieId: Long): Boolean {
        return dao.isFounded(movieId) > 0
    }

    fun deleteMovieById(movieId: Long) {
        dao.deleteMovieById(movieId)
    }

}