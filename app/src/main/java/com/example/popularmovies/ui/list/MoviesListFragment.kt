package com.example.popularmovies.ui.list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.popularmovies.common.utill.Constant
import com.example.popularmovies.common.state.UiState
import com.example.popularmovies.common.views.EndlessScrollListener
import com.example.popularmovies.databinding.FragmentMoviesListBinding
import com.example.popularmovies.domain.model.Movie
import com.example.popularmovies.ui.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class MoviesListFragment : Fragment(),MovieAdapter.MovieClickListener {
lateinit var binding: FragmentMoviesListBinding
private val viewModel by viewModels<HomeViewModel> ()
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private val navController: NavController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMoviesListBinding.inflate(layoutInflater,container,false)



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()
        setupMoviesRecyclerView()
        observeState()

    }

    private fun setClickListeners() = binding?.apply {
        btnTryAgain.setOnClickListener {
            viewModel.apply {
                resetList()
                loadMovies()
            }
        }
    }



    private fun setupMoviesRecyclerView() = binding.rvPhotos.apply {
        layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false).also { linearLayoutManager = it }
        setHasFixedSize(true)
        movieAdapter = MovieAdapter(this@MoviesListFragment)
        adapter = movieAdapter
        addOnScrollListener(
            object :EndlessScrollListener(linearLayoutManager , Constant.PAGE_SIZE){
                override fun onLoadMore() = viewModel.loadMovies()
                override fun isLastPage(): Boolean = viewModel.isLastPage
                override fun isLoading(): Boolean = viewModel.isLoading

            }
        )

    }

    private fun showLoading(isLoading: Boolean) = binding.progressBarLoading.apply {
        isVisible = isLoading
    }

    private fun showEmptyView(isVisible: Boolean) = binding.layoutEmptyView.apply {
        this.isVisible = isVisible
    }
    private fun observeState() {
        viewModel.movieListState.observe(viewLifecycleOwner) {
            showLoading(it is UiState.Loading || it is UiState.LoadMore)
            showEmptyView(it is UiState.Empty)
            when (it) {
                is UiState.Success ->{
                    movieAdapter.submitList(it.data)

                }
                else -> Unit
            }
        }
    }

    override fun onMovieClick(position: Int, movie: Movie) {
        navController.navigate(
            MoviesListFragmentDirections.actionMoviesListFragmentToMovieDetailsFragment(
                movie
            )
        )
    }


}