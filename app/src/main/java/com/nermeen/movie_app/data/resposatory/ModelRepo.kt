package com.nermeen.movie_app.data.resposatory

import android.util.Log
import com.nermeen.movie_app.data.dataSource.remoteDataSource.ApiDataSource
import com.nermeen.movie_app.data.model.CategoryResponse
import com.nermeen.movie_app.data.model.DetailsResponse
import com.nermeen.movie_app.utils.Result
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class ModelRepo @Inject constructor(private val apiDataSource: ApiDataSource) : RemoteRepo {

    override suspend fun getCategory(category: String): Result<CategoryResponse?> {
        var result: Result<CategoryResponse?>
        try {
            val response = apiDataSource.getCategory(category)
            if (response.isSuccessful) {
                result = Result.Success(response.body())
                Log.i("ModelRepository", "Result $result")
            } else {
                Log.i("ModelRepository", "Error${response.errorBody()?.string()}")
                result = Result.Error(Exception("error"))
            }
        } catch (e: IOException) {
            result = Result.Error(e)
            Log.e("ModelRepository", "IOException ${e.message}")
        }
        return result
    }

    override suspend fun loadMoreMoviesForCategory(
        category: String,
        page: Int
    ): Result<CategoryResponse?> {
        var result: Result<CategoryResponse?>
        try {
            val response = apiDataSource.loadMoreMoviesForCategory(category, page)
            if (response.isSuccessful) {
                result = Result.Success(response.body())
                Log.i("ModelRepository", "Result $result")
            } else {
                Log.i("ModelRepository", " Error ${response.errorBody()?.string()}")
                result = Result.Error(Exception("error"))
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
                result = Result.Error(Exception("error"))
            }
        } catch (e: IOException) {
            result = Result.Error(e)
            Log.e("ModelRepository", "IOException ${e.message}")
        }
        return result

    }
}