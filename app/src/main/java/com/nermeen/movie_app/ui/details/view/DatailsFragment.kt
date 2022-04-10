package com.nermeen.movie_app.ui.details.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.nermeen.movie_app.R
import com.nermeen.movie_app.data.model.Movies
import com.nermeen.movie_app.databinding.FragmentDatailsBinding


class DatailsFragment : Fragment() {
    lateinit var viewModel: DetailsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val movie = it.getSerializable("movie") as Movies
            viewModel =
                ViewModelProvider(this, DetailsViewModelFactory(movie))[DetailsViewModel::class.java]
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDatailsBinding.inflate(inflater,container,false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.imageBack.setOnClickListener {
            findNavController().popBackStack()
        }
        return binding.root
    }


}