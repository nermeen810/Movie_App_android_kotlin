package com.nermeen.movie_app.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.nermeen.movie_app.ui.home.viewModel.HomeViewModel
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nermeen.movie_app.data.model.Movies
import com.nermeen.movie_app.databinding.MovieItemBinding
import com.nermeen.movie_app.utils.Constants

class MovieAdapter(
    private val homeViewModel: HomeViewModel
) : ListAdapter<Movies, MovieAdapter.MovieViewHolder>(MoviesDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int

    ): MovieViewHolder {
        return MovieViewHolder(
            MovieItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val temp = getItem(position)
        holder.bind(temp)
    }

    inner class MovieViewHolder(private val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movies: Movies) {
            Glide.with(binding.ivMoviePoster.context)
                .load(movies.poster_path?.let { Constants.createImageUrl(it) })
                .into(binding.ivMoviePoster)

            binding.tvMovieTitle.text = movies.title

            binding.card.setOnClickListener {
                homeViewModel.navigateToDetails(movies)
            }
        }

    }

}
