package com.nermeen.movie_app.data.resposatory

import com.nermeen.movie_app.data.model.Category
import com.nermeen.movie_app.data.model.Movies

interface LocalRepo {
    suspend fun getCategories(): List<Category>
    suspend fun getMovies(): List<Movies>
    suspend fun insertAllMovies(list: List<Movies>)
    suspend fun insertAllCategories(list: List<Category>)
}