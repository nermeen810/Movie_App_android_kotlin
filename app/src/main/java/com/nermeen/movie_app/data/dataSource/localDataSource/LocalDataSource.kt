package com.nermeen.movie_app.data.dataSource.localDataSource

import com.nermeen.movie_app.data.dataSource.remoteDataSource.ApiDataSource
import com.nermeen.movie_app.data.model.Category
import com.nermeen.movie_app.data.model.Movies
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val dao: MoviesDao) {

    fun getCategories(): List<Category> {
        return dao.getCategories()
    }

    fun getMovies(): List<Movies> {
        return dao.getMovies()
    }

    fun insertAllMovies(list: List<Movies>) {
        dao.insertAllMovies(list)
    }

    fun insertAllCategories(list: List<Category>) {
        dao.insertAllCategories(list)
    }
}