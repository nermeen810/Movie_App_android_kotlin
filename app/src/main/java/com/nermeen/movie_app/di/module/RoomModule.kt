package com.nermeen.movie_app.di.module

import android.content.Context
import androidx.room.Room
import com.nermeen.movie_app.data.dataSource.localDataSource.AppDatabase
import com.nermeen.movie_app.data.dataSource.localDataSource.MoviesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext applicationContext: Context): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "movies_db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideChannelDao(appDatabase: AppDatabase): MoviesDao {
        return appDatabase.moviesDao()
    }

}