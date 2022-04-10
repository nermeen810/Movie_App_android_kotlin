package com.nermeen.movie_app.ui.home

import android.view.View
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nermeen.movie_app.data.model.CategoryResponse
import com.nermeen.movie_app.data.model.Movies
import com.nermeen.movie_app.data.model.MoviesResponse
import com.nermeen.movie_app.data.resposatory.ModelRepo
import com.nermeen.movie_app.utils.Result
import com.nermeen.movie_app.utils.SingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val modelRepo: ModelRepo) : ViewModel() {

    val genres: MutableLiveData<CategoryResponse?> = MutableLiveData()
    val movies: MutableLiveData<MoviesResponse?> = MutableLiveData()
    var lastSelectedPos = 0
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val navigationToDetailsLiveDate: MutableLiveData<SingleEvent<Movies>> = MutableLiveData()

    private var pageNumber = 1

    init {
        isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            when (val categoryResponse = modelRepo.getCategory()) {
                is Result.Success -> {
                    categoryResponse.data?.genres?.let {
                        modelRepo.insertAllCategories(it)
                    }
                    genres.postValue(categoryResponse.data)
                    categoryResponse.data?.genres?.firstOrNull()?.id?.let {
                        getMovieByCategoryId(it)
                    }

                }

                is Result.Error -> {
                    errorMessage.postValue(categoryResponse.exception.localizedMessage)
                    modelRepo.getCategories().let {
                        genres.postValue(CategoryResponse(it))
                        val result = modelRepo.getMovies().filter { movie ->
                            movie.genre_ids.contains(it.firstOrNull()?.id)
                        }
                        movies.postValue(MoviesResponse(0, result, 0, 0))
                        isLoading.postValue(false)
                    }

                }
            }

        }

    }

    fun navigateToDetails(movies: Movies) {
        navigationToDetailsLiveDate.postValue(SingleEvent(movies))
    }

    fun getMovieByCategoryId(id: Int) {
        isLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            val moviesResponse =
                modelRepo.loadMoreMoviesForCategory(id.toString(), pageNumber)
            when (moviesResponse) {
                is Result.Success -> {
                    moviesResponse.data?.results?.let {
                        modelRepo.insertAllMovies(it)
                    }

                    movies.postValue(moviesResponse.data)
                    isLoading.postValue(false)
                }

                is Result.Error -> {
                    errorMessage.postValue(moviesResponse.exception.localizedMessage)
                    val result = modelRepo.getMovies().filter {
                        it.genre_ids.contains(id)
                    }
                    movies.postValue(MoviesResponse(0, result, 0, 0))
                    isLoading.postValue(false)
                }
            }
        }
    }
}