package com.nermeen.movie_app.data.dataSource.localDataSource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nermeen.movie_app.data.model.Category
import com.nermeen.movie_app.data.model.Movies

@Dao
interface MoviesDao {
    @Query("SELECT * FROM Movies")
    fun getMovies(): List<Movies>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMovies(movies: List<Movies>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCategories(categories: List<Category>)

    @Query("SELECT * FROM Category")
    fun getCategories(): List<Category>
}