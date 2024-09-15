package com.nermeen.movie_app.ui.details.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.nermeen.movie_app.R
import com.nermeen.movie_app.databinding.FragmentDatailsBinding
import com.nermeen.movie_app.ui.details.viewModel.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private val viewModel: DetailsViewModel  by viewModels()
    private lateinit var binding :FragmentDatailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val movieId = it.getLong("movieId")
            viewModel.getMovieDetails(movieId)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDatailsBinding.inflate(inflater,container,false)
        setUpUI()
        observeData()
        return binding.root
    }

    private fun observeData() {
        observeShowError()

    }

    private fun setUpUI(){
       binding.viewModel = viewModel
       binding.lifecycleOwner = viewLifecycleOwner
       binding.imageBack.setOnClickListener {
           findNavController().popBackStack()
       }
   }

    private fun observeShowError(){
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            showMessage(it)
        }
    }

    private fun showMessage(msg: String) {
        Snackbar.make(requireView(), msg, Snackbar.LENGTH_INDEFINITE)
            .setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE).setBackgroundTint(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.teal
                )
            )
            .setActionTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            .setAction("ok")
            {
            }.show()
    }

}