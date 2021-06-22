package com.diego.duarte.popularmovieskotlin.movie.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.Downsampler
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.diego.duarte.popularmovieskotlin.R
import com.diego.duarte.popularmovieskotlin.data.model.Video
import com.diego.duarte.popularmovieskotlin.movie.MoviePresenter

class VideosAdapter(val presenter: MoviePresenter): RecyclerView.Adapter<VideosAdapter.VideoViewHolder>() {


    inner class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), VideoItemView {

        private val imageThumbnail: ImageView = itemView.findViewById(R.id.video_image_thumbnail)

        init {
            itemView.setOnClickListener {
                presenter.onTrailerClicked(adapterPosition)
            }
        }

        override fun bindItem(video: Video) {
            Glide
                .with(itemView.context)
                .load(itemView.context.getString(R.string.url_youtube_thumbnail,video.key))
                .placeholder(R.color.gray)
                .centerInside()
                .set(Downsampler.DECODE_FORMAT, DecodeFormat.PREFER_RGB_565)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageThumbnail)
        }
        fun clearVew (){
            Glide.with(itemView.context).clear(imageThumbnail)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        presenter.onBindItemView(holder, position)

    }

    override fun getItemCount(): Int {
        return presenter.itemCount
    }

    override fun onViewRecycled(holder: VideoViewHolder) {
        super.onViewRecycled(holder)
        holder.clearVew()
    }

    fun insertItems(videos: List<Video>){
        val count = this.itemCount
        presenter.setList(videos)
        notifyItemRangeInserted(count, videos.size)
    }
}