package com.nermeen.movie_app.ui.home.view

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.nermeen.movie_app.R
import com.nermeen.movie_app.databinding.FragmentHomeBinding
import com.nermeen.movie_app.ui.home.CategoryAdapter
import com.nermeen.movie_app.ui.home.viewModel.HomeViewModel
import com.nermeen.movie_app.ui.home.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    lateinit var binding : FragmentHomeBinding
    lateinit var categoryAdapter : CategoryAdapter
    lateinit var movieAdapter : MovieAdapter
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
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        setUpUI()
        observeData()
        registerConnectivityNetworkMonitor()
        return binding.root
    }

    private fun setUpUI(){
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        categoryAdapter = CategoryAdapter(viewModel)
        binding.categoriesRecycle.adapter = categoryAdapter
        movieAdapter = MovieAdapter(viewModel)
        binding.moviesRecycle.layoutManager = GridLayoutManager(context, 2)
        binding.moviesRecycle.adapter = adapter
    }

   private fun observeData(){
       observeShowError()
       observeNavToDetails()
       observeCategories()
       observeMovies()
   }

    private fun observeShowError(){
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            showMessage(it)
        }
    }

    private fun observeNavToDetails(){
        viewModel.navigationToDetailsLiveDate.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { movie ->
                val bundle = Bundle()
                bundle.putSerializable("movie",movie)
                findNavController().navigate(R.id.action_homeFragment_to_datailsFragment,bundle)
            }
        }
    }

    private fun observeCategories(){
        viewModel.genres.observe(viewLifecycleOwner) {
            categoryAdapter.submitList(it?.genres)
        }
    }

    private fun observeMovies(){
        viewModel.movies.observe(viewLifecycleOwner) {
            movieAdapter.submitList(it?.results)
        }
    }

   private fun showMessage(msg:String){
       Snackbar.make(requireView(), msg, Snackbar.LENGTH_INDEFINITE)
           .setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE).setBackgroundTint(
               ContextCompat.getColor(
                   requireContext(),
                   R.color.teal
               )
           )
           .setActionTextColor( ContextCompat.getColor(
               requireContext(),
               R.color.white
           ))
           .setAction("ok")
           {
           }.show()
    }

    private fun registerConnectivityNetworkMonitor() {
        if (requireContext() != null) {
            val connectivityManager = requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val builder = NetworkRequest.Builder()
            connectivityManager.registerNetworkCallback(builder.build(),
                object : ConnectivityManager.NetworkCallback() {
                    override fun onAvailable(network: Network) {
                        super.onAvailable(network)
                        if (activity != null) {
                            activity!!.runOnUiThread {
                                viewModel.getCategories()
                            }
                        }
                    }

                    override fun onLost(network: Network) {
                        super.onLost(network)
                        if (activity != null) {
                            activity!!.runOnUiThread {
                             showMessage(getString(R.string.network_error_msg))
                            }
                        }
                    }
                }
            )
        }
    }
}