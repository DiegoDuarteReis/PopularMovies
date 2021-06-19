package com.diego.duarte.popularmovieskotlin.movies.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.resource.bitmap.Downsampler
import com.diego.duarte.popularmovieskotlin.BuildConfig
import com.diego.duarte.popularmovieskotlin.R
import com.diego.duarte.popularmovieskotlin.data.model.Movie
import com.diego.duarte.popularmovieskotlin.movies.presenter.MoviesPresenter

class MoviesAdapter(private val presenter: MoviesPresenter) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), MovieItemView {

        private val posterView: ImageView

        init {
            posterView = itemView.findViewById(R.id.movie_image_poster)
            itemView.setOnClickListener {
                presenter.onItemClicked(adapterPosition)
            }
        }

        override fun bindItem(movie: Movie) {
            Glide
                .with(itemView.context)
                .load(BuildConfig.TMDB_IMAGE_URL + movie.poster_path)
                .placeholder(R.drawable.image_movie_placeholder)
                .centerInside()
                .set(Downsampler.DECODE_FORMAT, DecodeFormat.PREFER_RGB_565)
                .into(posterView)
        }
        fun clearVew (){
            Glide.with(itemView.context).clear(posterView)
        }
    }
    

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)

        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        presenter.onBindItemView(holder, position)

    }

    override fun getItemCount(): Int {
        return presenter.itemCount ?: 0
    }

    override fun onViewRecycled(holder: MovieViewHolder) {
        super.onViewRecycled(holder)
        holder.clearVew()
    }

    fun insertItems(movie: List<Movie>){
        presenter.setList(movie)
        notifyDataSetChanged()
    }

    fun getList(): List<Movie> = presenter.listMovies()

}