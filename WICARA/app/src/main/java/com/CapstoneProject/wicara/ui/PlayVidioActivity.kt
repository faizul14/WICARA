package com.CapstoneProject.wicara.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.CapstoneProject.wicara.R
import com.CapstoneProject.wicara.databinding.ActivityPlayVidioBinding
import com.CapstoneProject.wicara.network.VidioResponseItem
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PlayVidioActivity : AppCompatActivity() {
    private var bindig : ActivityPlayVidioBinding? = null

    private val viewBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityPlayVidioBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        bindig = ActivityPlayVidioBinding.inflate(layoutInflater)
        setContentView(viewBinding?.root)

        val data = intent.getParcelableExtra<VidioResponseItem>(EXTRA_DATA)

        val player = ExoPlayer.Builder(this).build()
        viewBinding.exPlayer.player = player


        lifecycleScope.launch(Dispatchers.Default){
            val mediaItem = MediaItem.fromUri("https://github.com/dicodingacademy/assets/releases/download/release-video/VideoDicoding.mp4")

            withContext(Dispatchers.Main){
                player.setMediaItem(mediaItem)
                player.prepare()
            }
        }
//        val mediaItem = MediaItem.fromUri("https://github.com/dicodingacademy/assets/releases/download/release-video/VideoDicoding.mp4")
//        player.setMediaItem(mediaItem)
//        player.prepare()
    }

    override fun onDestroy() {
        super.onDestroy()
        bindig = null
    }
    companion object{
        const val EXTRA_DATA = "extra_data"
    }
}