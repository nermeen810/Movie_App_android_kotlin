package com.nermeen.movie_app.ui.home.viewModel

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

    private  val _genres: MutableLiveData<CategoryResponse?> = MutableLiveData()
    private  val _movies: MutableLiveData<MoviesResponse?> = MutableLiveData()
    private  val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    private  val _errorMessage: MutableLiveData<String> = MutableLiveData()
    private  val _navigationToDetailsLiveDate: MutableLiveData<SingleEvent<Movies>> = MutableLiveData()
    private  var pageNumber = 1
    var lastSelectedPos = 0

    val genres: LiveData<CategoryResponse?> = _genres

    val movies: LiveData<MoviesResponse?>
        get() = _movies

    val isLoading: LiveData<MoviesResponse?>
        get() = _isLoading

    val errorMessage: LiveData<String>
        get() = _errorMessage

    val navigationToDetailsLiveDate: LiveData<SingleEvent<Movies>>
        get() = _navigationToDetailsLiveDate

    init {
        getCategories()
    }

    fun  getCategories() {
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