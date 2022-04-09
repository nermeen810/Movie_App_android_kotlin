package com.nermeen.movie_app.di.module

import android.content.Context
import androidx.room.Room
import com.nermeen.movie_app.data.dataSource.localDataSource.AppDatabase
import com.nermeen.movie_app.data.dataSource.localDataSource.MoviesDao
import com.nermeen.movie_app.data.dataSource.remoteDataSource.ApiService
import com.nermeen.movie_app.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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