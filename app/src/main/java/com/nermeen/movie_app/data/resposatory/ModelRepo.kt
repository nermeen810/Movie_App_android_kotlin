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

    override suspend fun getCategory(): Result<CategoryResponse?> {
        var result: Result<CategoryResponse?>
        try {
            val response = apiDataSource.getCategory()
            if (response.isSuccessful) {
                result = Result.Success(response.body())
                Log.i("ModelRepository", "Result $result")
            } else {
                Log.i("ModelRepository", "Error${response.errorBody()?.string()}")
                result = Result.Error(Exception(response.message()))
            }
        } catch (e: IOException) {
            result = Result.Error(e)
            Log.e("ModelRepository", "IOException ${e.message}")
        }
        return result
    }

    override suspend fun loadMoreMoviesForCategory(
        categoryId: String,
        page: Int
    ): Result<MoviesResponse?> {
        var result: Result<MoviesResponse?>
        try {
            val response = apiDataSource.loadMoreMoviesForCategory(categoryId, page)
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
            val response = apiDataSource.getMovieDetails(id)
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

    override suspend fun getCategories(): List<Category> {
        return localDataSource.getCategories()
    }

    override suspend fun getMovies(): List<Movies> {
        return localDataSource.getMovies()
    }

    override suspend fun insertAllMovies(list: List<Movies>) {
        localDataSource.insertAllMovies(list)
    }

    override suspend fun insertAllCategories(list: List<Category>) {
        localDataSource.insertAllCategories(list)
    }

}