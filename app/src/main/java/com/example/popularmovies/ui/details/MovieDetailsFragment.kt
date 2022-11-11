package com.example.popularmovies.ui.details

import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.popularmovies.R
import com.example.popularmovies.common.utill.Constant
import com.example.popularmovies.databinding.FragmentMovieDetailsBinding
import com.example.popularmovies.databinding.FragmentMoviesListBinding
import com.example.popularmovies.domain.model.Movie


class MovieDetailsFragment : Fragment() {
    val args by navArgs<MovieDetailsFragmentArgs>()
    lateinit var binding: FragmentMovieDetailsBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMovieDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        feedMovieDetails(args.movie)
    }

    private fun feedMovieDetails(movie: Movie) = binding?.apply {
        Glide.with(root)
            .asBitmap()
            .load(Constant.IMAGE_URL + movie.backdropPath)
            .listener(object : RequestListener<Bitmap> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Bitmap?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    movieIV.setImageBitmap(resource)
                    resource?.let { bitmap ->
                        Palette.Builder(bitmap).generate {
                            it?.let { palette ->
                                context?.let { context ->
                                    val dominantColor = palette.getDominantColor(
                                        ContextCompat.getColor(context, R.color.black)
                                    )
                                    root.setBackgroundColor(dominantColor)
                                }
                            }
                        }
                    }
                    return true
                }
            })
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(movieIV)

        movieTitleTV.text = movie.title
        movieDateTV.text = movie.releaseDate
        movieRateTV.text = movie.voteAverage
        movieOverviewTV.text = movie.overview
    }
}