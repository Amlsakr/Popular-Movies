package com.example.popularmovies.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.popularmovies.common.utill.Constant
import com.example.popularmovies.databinding.MovieItemBinding
import com.example.popularmovies.domain.model.Movie

class MovieAdapter (
    val listener: MovieClickListener
        ) :
    ListAdapter<Any, RecyclerView.ViewHolder>(MoviesDiffCallback){


    private object MoviesDiffCallback : DiffUtil.ItemCallback<Any>() {
        override fun areItemsTheSame(
            oldItem: Any,
            newItem: Any,
        ): Boolean {
            return (oldItem is Movie && newItem is Movie && oldItem.id == newItem.id)
        }

        override fun areContentsTheSame(
            oldItem: Any,
            newItem: Any,
        ): Boolean {
            return (oldItem is Movie && newItem is Movie && oldItem == newItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
      return MoviesViewHolder(MovieItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is MoviesViewHolder -> holder.bindItem(item as Movie)

        }

    }

    inner class MoviesViewHolder(private val binding: MovieItemBinding)
        :RecyclerView.ViewHolder(binding.root){
            fun bindItem(movie: Movie) = binding.apply {

                Glide.with(root)
                    .load(Constant.IMAGE_URL +movie.backdropPath)
                    .into(binding.movieIV)
                movieTitleTV.text = movie.title

                root.setOnClickListener {
                    listener.onMovieClick(bindingAdapterPosition ,movie)
                }
            }


        }

    interface MovieClickListener {
        fun onMovieClick(position: Int, movie: Movie)
    }

}