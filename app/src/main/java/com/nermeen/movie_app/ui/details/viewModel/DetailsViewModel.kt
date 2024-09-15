package com.nermeen.movie_app.ui.details.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nermeen.movie_app.data.model.DetailsResponse
import com.nermeen.movie_app.data.resposatory.ModelRepo
import com.nermeen.movie_app.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val modelRepo: ModelRepo) : ViewModel() {
    private val _movie: MutableLiveData<DetailsResponse> = MutableLiveData()
    private  val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    private  val _errorMessage: MutableLiveData<String> = MutableLiveData()

    val movies: LiveData<DetailsResponse>
        get() = _movie

    val isLoading: LiveData<Boolean>
        get() = _isLoading

    val errorMessage: LiveData<String>
        get() = _errorMessage

    fun getMovieDetails(id: Long) {
        _isLoading.postValue(true)
        viewModelScope.launch {
        }
        viewModelScope.launch(Dispatchers.IO) {
            when (val movieResponse = modelRepo.getMovieDetails(id)) {
                is Result.Success -> {
                    _movie.postValue(movieResponse.data)
                    _isLoading.postValue(false)
                }

                is Result.Error -> {
                    _errorMessage.postValue(movieResponse.exception.localizedMessage)
                    _isLoading.postValue(false)
                }
            }
        }
    }
}