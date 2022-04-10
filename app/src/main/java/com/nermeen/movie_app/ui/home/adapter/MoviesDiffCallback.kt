package com.nermeen.movie_app.ui.home.adapter

import androidx.recyclerview.widget.DiffUtil
import com.nermeen.movie_app.data.model.Movies

class MoviesDiffCallback : DiffUtil.ItemCallback<Movies>() {
    override fun areItemsTheSame(oldItem: Movies, newItem: Movies): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movies, newItem: Movies): Boolean {
        return oldItem == newItem
    }
}