package com.nermeen.movie_app.ui.home

import com.nermeen.movie_app.data.model.MoviesResponse
import com.nermeen.movie_app.databinding.CategoryItemBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nermeen.movie_app.R
import com.nermeen.movie_app.data.model.Category
import com.nermeen.movie_app.data.model.CategoryResponse
import java.util.*

class CategoryAdapter(private val homeViewModel: HomeViewModel) :
    ListAdapter<Category, CategoryAdapter.CategoryViewHolder>(CategoryDiffCallback()) {

    var lastSelectedCard: CardView? = null
    var lastSelectedText: TextView? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int

    ): CategoryViewHolder {
        return CategoryViewHolder(
            CategoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        if (lastSelectedCard == null && position == homeViewModel.lastSelectedPos) {
            lastSelectedCard = holder.binding.categoryCard
            lastSelectedText = holder.binding.categoryName

            lastSelectedCard?.setCardBackgroundColor(
                ContextCompat.getColor(
                    holder.binding.categoryCard.context,
                    R.color.teal
                )
            )
            lastSelectedText?.setTextColor(
                ContextCompat.getColor(
                    holder.binding.categoryCard.context,
                    R.color.white
                )
            )
        }
        val temp = getItem(position)
        holder.bind(temp,position)
    }

    inner class CategoryViewHolder(val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category,position: Int) {

            binding.categoryName.text = category.name
            binding.categoryCard.setOnClickListener {
                homeViewModel.getMovieByCategoryId(category.id)
                homeViewModel.lastSelectedPos = position
                lastSelectedCard?.setCardBackgroundColor(
                    ContextCompat.getColor(
                        it.context,
                        R.color.white
                    )
                )
                lastSelectedText?.setTextColor(
                    ContextCompat.getColor(
                        it.context,
                        R.color.black
                    )
                )

                binding.categoryCard.setCardBackgroundColor(
                    ContextCompat.getColor(
                        it.context,
                        R.color.teal
                    )
                )
                binding.categoryName.setTextColor(
                    ContextCompat.getColor(
                        it.context,
                        R.color.white
                    )
                )
                lastSelectedCard = binding.categoryCard
                lastSelectedText = binding.categoryName
            }
        }

    }

}
