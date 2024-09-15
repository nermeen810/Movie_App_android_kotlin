package com.nermeen.movie_app.data.resposatory

import android.util.Log
import com.nermeen.movie_app.data.dataSource.localDataSource.LocalDataSource
import com.nermeen.movie_app.data.dataSource.remoteDataSource.ApiDataSource
import com.nermeen.movie_app.data.model.*
import com.nermeen.movie_app.utils.Result
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class ModelRepo @Inject constructor(
    private val apiDataSource: ApiDataSource,
    private val localDataSource: LocalDataSource
) : RemoteRepo, LocalRepo {

    override suspend fun loadMoreMovies(
        page: Int
    ): Result<MoviesResponse?> {
        var result: Result<MoviesResponse?>
        try {
            val response = apiDataSource.loadMoreMovies(page)
            if (response.isSuccessful) {
                result = Result.Success(response.body())
                Log.i("ModelRepository", "Result $result")
            } else {
                Log.i("ModelRepository", " Error ${response.errorBody()?.string()}")
                result = Result.Error(Exception(response.message()))
            }
        } catch (e: IOException) {
            result = Result.Error(e)
            Log.e("ModelRepository", "IOException ${e.message}")
        }
        return result
    }

    override suspend fun getMovieDetails(id: Long): Result<DetailsResponse?> {
        var result: Result<DetailsResponse?>
        try {
            println("XXX: Test test")
            val response = apiDataSource.getMovieDetails(id)
            println("XXX: ${response.raw().request.url}")
            if (response.isSuccessful) {
                result = Result.Success(response.body())
                Log.i("ModelRepository", "Result $result")
            } else {
                Log.i("ModelRepository", " Error ${response.errorBody()?.string()}")
                result = Result.Error(Exception(response.message()))
            }
        } catch (e: IOException) {
            result = Result.Error(e)
            Log.e("ModelRepository", "IOException ${e.message}")
        }
        return result

    }

    override suspend fun getMovies(): List<Movies> {
        return localDataSource.getMovies()
    }

    override suspend fun insertMovie(movie: Movies) {
        localDataSource.insertMovie(movie)
    }

    override suspend fun isAddedToFavorite(movieId: Long): Boolean {
        return localDataSource.isAddedToFavorite(movieId)
    }

    override suspend fun deleteMovieById(movieId: Long) {
        localDataSource.deleteMovieById(movieId)
    }

}