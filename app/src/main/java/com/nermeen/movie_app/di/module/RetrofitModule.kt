package com.nermeen.movie_app.di.module

import com.nermeen.movie_app.data.dataSource.remoteDataSource.ApiService
import com.nermeen.movie_app.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideClient())
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieRestApi(retrofit: Retrofit) : ApiService{
        return  retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideClient() : OkHttpClient {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level  =  HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }


}