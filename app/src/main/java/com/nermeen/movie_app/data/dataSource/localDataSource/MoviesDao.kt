package com.nermeen.movie_app.data.dataSource.localDataSource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nermeen.movie_app.data.model.Movies

@Dao
interface MoviesDao {
    @Query("SELECT * FROM Movies")
    fun getMovies(): List<Movies>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movies)

    @Query("SELECT COUNT(*) FROM Movies WHERE id = :movieId")
    fun isFounded(movieId: Long): Int

    @Query("DELETE FROM Movies WHERE id = :movieId")
    fun deleteMovieById(movieId: Long)
}
