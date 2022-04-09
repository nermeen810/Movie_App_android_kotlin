package com.nermeen.movie_app.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.nermeen.movie_app.R
import com.nermeen.movie_app.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        val adapterC = CategoryAdapter(viewModel)
        binding.categoriesRecycle.adapter = adapterC

        viewModel.genres.observe(viewLifecycleOwner) {
            adapterC.submitList(it?.genres)
        }
        val adapter = MovieAdapter(viewModel)
        binding.moviesRecycle.layoutManager = GridLayoutManager(context,2)
        binding.moviesRecycle.adapter = adapter

        viewModel.movies.observe(viewLifecycleOwner) {
            adapter.submitList(it?.results)
        }
        return binding.root
    }

}