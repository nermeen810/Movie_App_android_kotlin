package com.nermeen.movie_app.ui.home.viewModel

import androidx.lifecycle.*
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

    private  val _movies: MutableLiveData<MoviesResponse?> = MutableLiveData()
    private  val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    private  val _errorMessage: MutableLiveData<String> = MutableLiveData()
    private  val _navigationToDetailsLiveDate: MutableLiveData<SingleEvent<Long>> = MutableLiveData()
    private  var pageNumber = 1

    val movies: LiveData<MoviesResponse?>
        get() = _movies

    val isLoading: LiveData<Boolean>
        get() = _isLoading

    val errorMessage: LiveData<String>
        get() = _errorMessage

    val navigationToDetailsLiveDate: LiveData<SingleEvent<Long>>
        get() = _navigationToDetailsLiveDate

    init {
        getMovies()
    }

    fun navigateToDetails(movieId: Long) {
        _navigationToDetailsLiveDate.postValue(SingleEvent(movieId))
    }

    fun addToFavorite(movies: Movies) {
        viewModelScope.launch(Dispatchers.IO) {
            modelRepo.insertMovie(movies)
        }
    }
    fun removeFromFavorite(movieId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            modelRepo.deleteMovieById(movieId)
        }
    }


    suspend fun isAddedToFavorite(movieId: Long): Boolean {
        val isAdded = viewModelScope.async(Dispatchers.IO) {
            modelRepo.isAddedToFavorite(movieId)
        }
        return isAdded.await()
    }


    fun getMovies() {
        _isLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            val moviesResponse =
                modelRepo.loadMoreMovies(pageNumber)
            when (moviesResponse) {
                is Result.Success -> {
                    _movies.postValue(moviesResponse.data)
                    _isLoading.postValue(false)
                }

                is Result.Error -> {
                    _errorMessage.postValue(moviesResponse.exception.localizedMessage)
                    _isLoading.postValue(false)
                }
            }
        }
    }
}