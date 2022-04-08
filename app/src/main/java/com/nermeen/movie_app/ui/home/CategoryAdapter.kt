package com.nermeen.movie_app.ui.home

import com.nermeen.movie_app.data.model.CategoryResponse
import com.nermeen.movie_app.databinding.CategoryItemBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class CategoryAdapter (private var categoryList: ArrayList<CategoryResponse>,
                       private val homeViewModel: HomeViewModel) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>(){

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

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val temp = categoryList[position]

        holder.movieAdapter.addItems(temp!!.results)
        holder.binding.textView.text = temp.categoryName
        holder.binding.card.setOnClickListener {
            homeViewModel.navToServiceDetails(historyList[position])
        }

    }



    fun updateItems(newList: List<CategoryResponse>) {
        //     historyList.clear()
        categoryList.addAll(newList)
        notifyDataSetChanged()
    }
    fun clearList() {
        categoryListcategoryList.clear()
        notifyDataSetChanged()
    }
    inner class CategoryViewHolder(val binding:  CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root){


    }

}
