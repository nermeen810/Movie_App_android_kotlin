package com.nermeen.movie_app.data.dataSource.localDataSource

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nermeen.movie_app.data.model.Movies

@Database(entities = [Movies::class], version = 1)
@TypeConverters(AppTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}