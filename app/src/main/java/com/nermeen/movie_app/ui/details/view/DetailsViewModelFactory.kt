package com.nermeen.movie_app.ui.details.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nermeen.movie_app.data.model.Movies

class DetailsViewModelFactory(val movies: Movies) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            return DetailsViewModel(movies) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
