package com.nermeen.movie_app.ui.home

import androidx.recyclerview.widget.DiffUtil
import com.nermeen.movie_app.data.model.Category
import com.nermeen.movie_app.data.model.CategoryResponse
import com.nermeen.movie_app.data.model.MoviesResponse

class CategoryDiffCallback : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }
}