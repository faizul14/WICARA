package com.CapstoneProject.wicara.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.MediaStore
import android.util.SparseArray
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YouTubeExtractor
import at.huber.youtubeExtractor.YtFile
import com.CapstoneProject.wicara.databinding.ActivityPlayVidioBinding
import com.CapstoneProject.wicara.network.VidioResponseItem
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.MergingMediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
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
        setContentView(viewBinding?.root)
        supportActionBar?.hide()

        val data = intent.getParcelableExtra<VidioResponseItem>(EXTRA_DATA)

        val player = ExoPlayer.Builder(this).build()
        viewBinding.exPlayer.player = player

//
//        lifecycleScope.launch(Dispatchers.Default){
//            val mediaItem = MediaItem.fromUri("https://github.com/dicodingacademy/assets/releases/download/release-video/VideoDicoding.mp4")
//
//            withContext(Dispatchers.Main){
//                player.setMediaItem(mediaItem)
//                player.prepare()
//            }
//        }

//        object : YouTubeExtractor(this) {
//            fun onExtractionComplete(ytFiles: SparseArray<YtFile>?, vMeta: VideoMeta?) {
//                if (ytFiles != null) {
//                    val itag = 22
//                    val downloadUrl: String = ytFiles[itag].getUrl()
//                }
//            }
//        }.extract(youtubeLink)

        val link = data?.linkYoutube
        lifecycleScope.launch(Dispatchers.Default){
            object : YouTubeExtractor(applicationContext){
                override fun onExtractionComplete(
                    ytFiles: SparseArray<YtFile>?,
                    videoMeta: VideoMeta?
                ) {
                    if (ytFiles != null){
                        val itag = 22
//                    val audioTag = 140
                        val vidioUrl : String = ytFiles[itag].url
//                    val audioUrl : String = ytFiles[audioTag].url
//
//                    val audioSource : MediaSource = ProgressiveMediaSource
//                        .Factory(DefaultHttpDataSource.Factory())
//                        .createMediaSource(MediaItem.fromUri(audioUrl))
//                    val vidioSource : MediaSource = ProgressiveMediaSource
//                        .Factory(DefaultHttpDataSource.Factory())
//                        .createMediaSource(MediaItem.fromUri(vidioUrl))

//                    player.setMediaSource(MergingMediaSource(
//                        true, vidioSource, audioSource
//                    ), true)
//                   player.setMediaSource(MediaStore.Video.Media)
//                    player.prepare()
//                    player.playWhenReady
                        player.setMediaItem(MediaItem.fromUri(vidioUrl))
                        player.prepare()
                        player.playWhenReady

                    }
                }

            }.extract(link)
        }





//        val mediaItem = MediaItem.fromUri("https://github.com/dicodingacademy/assets/releases/download/release-video/VideoDicoding.mp4")
//        player.setMediaItem(mediaItem)
//        player.prepare()
    }

    private fun init(){}

    override fun onDestroy() {
        super.onDestroy()
        bindig = null
    }
    companion object{
        const val EXTRA_DATA = "extra_data"
    }
}