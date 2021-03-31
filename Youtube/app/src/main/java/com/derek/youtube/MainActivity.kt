package com.derek.youtube

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.recyclerview.widget.LinearLayoutManager

import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.video_detail.*
import kotlinx.android.synthetic.main.video_detail_content.*
import kotlinx.coroutines.*
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class MainActivity : AppCompatActivity() {
    private lateinit var videoAdapter: VideoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        supportActionBar?.title = ""

        val videos: MutableList<VideosModel> = mutableListOf()
        videoAdapter = VideoAdapter(videos) { video ->
           showOverLayView(video)
        }

        view_layer.alpha = 0f
        rv_main.layoutManager = LinearLayoutManager(this)
        rv_main.adapter = videoAdapter

        CoroutineScope(Dispatchers.IO).launch {
            val res: Deferred<ListVideo?> = async { getVideo() }
            val listVideo: ListVideo? = res.await()
            withContext(Dispatchers.Main) {
                listVideo?.let {
                    videos.clear()
                    videos.addAll(listVideo.data)
                    videoAdapter.notifyDataSetChanged()
//                    progress_recycler.visibility = View.GONE
                    motion_container.removeView(progress_recycler)
                }
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun showOverLayView(video:VideosModel){
        view_layer.animate().apply {
            duration = 400
            alpha(0.5f)
        }
        motion_container.setTransitionListener(object:MotionLayout.TransitionListener{
            override fun onTransitionStarted(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int
            ) {
                println("Transition Started $startId - $endId")
            }

            override fun onTransitionChange(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int,
                progress: Float
            ) {
                
                if(progress > 0.5f)
                    view_layer.alpha = 1.0f - progress
                else
                    view_layer.alpha = 0.5f


            }

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
               println("Completed $currentId")
            }

            override fun onTransitionTrigger(
                motionLayout: MotionLayout?,
                triggerId: Int,
                positive: Boolean,
                progress: Float
            ) {
                println("Trigger $triggerId - $positive - $progress")
            }

        })
        val detailAdapter = VideoDetailAdapter(videos())
        rv_similar.layoutManager = LinearLayoutManager(this)
        rv_similar.setHasFixedSize(true)
        rv_similar.adapter = detailAdapter
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