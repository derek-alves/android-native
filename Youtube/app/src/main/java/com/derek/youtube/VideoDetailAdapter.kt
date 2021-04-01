package com.derek.youtube


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_video.view.*
import kotlinx.android.synthetic.main.item_video.view.video_thumbnail
import kotlinx.android.synthetic.main.item_video.view.video_title
import kotlinx.android.synthetic.main.video_detail_item.view.*

class VideoDetailAdapter(private val videos: List<VideosModel>) : RecyclerView.Adapter<VideoDetailAdapter.VideoHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoHolder =
        VideoHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.video_detail_item,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = videos.size

    override fun onBindViewHolder(holder: VideoHolder, position: Int) {
        holder.bind(videos[position])
    }

    inner class VideoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(video: VideosModel) {
            with(itemView) {
                Picasso.get().load(video.thumbnailUrl).into(video_detail_thumbnail)
                video_detail_title.text = video.title
                video_detail_info.text = context.getString(R.string.info,
                    video.publisher.name, video.viewsCountLabel, video.publishedAt.formatted())
            }
        }
    }
}