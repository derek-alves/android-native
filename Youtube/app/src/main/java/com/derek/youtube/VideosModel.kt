package com.derek.youtube

import java.text.SimpleDateFormat
import java.util.*

data class VideosModel(
    val id: String,
    val thumbnailUrl: String,
    val title: String,
    val publishedAt: Date,
    val viewsCount: Long,
    val viewsCountLabel: String,
    val duration: Int,
    val videoUrl: String,
    val publisher: Publisher
)


data class Publisher(
    val id: String,
    val name: String,
    val pictureProfileUrl: String,
)

data class ListVideo(
    val status: Int,
    val data: List<VideosModel>
)

fun Date.formatted(): String = SimpleDateFormat("d MMM yyyy", Locale("pt", "BR")).format(this)