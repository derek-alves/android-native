package com.derek.youtube

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class MainActivity : AppCompatActivity() {
    private lateinit var videoAdapter:VideoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val videos: MutableList<VideosModel> = mutableListOf()
        videoAdapter = VideoAdapter(videos) { video: VideosModel ->
            println(video)
        }
        rv_main.layoutManager = LinearLayoutManager(this)
        rv_main.adapter = videoAdapter

        CoroutineScope(Dispatchers.IO).launch {
            val res: Deferred<ListVideo?> = async { getVideo() }
            val listVideo:ListVideo? = res.await()
            withContext(Dispatchers.Main){
                listVideo?.let {
                    videos.clear()
                    videos.addAll(listVideo.data)
                    videoAdapter.notifyDataSetChanged()
                    motion_container.removeView(progress_recycler)
                }
            }
        }

    }

    private fun getVideo(): ListVideo? {
        val client: OkHttpClient = OkHttpClient.Builder().build()

        val request: Request =
            Request.Builder().get().url("https://tiagoaguiar.co/api/youtube-videos").build()
        return try {
            val response: Response = client.newCall(request).execute()
            if (response.isSuccessful) {
                GsonBuilder().create().fromJson(response.body()?.string(), ListVideo::class.java)
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}