package com.xxunghee.androidtvexample.view.fragment

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.leanback.app.VideoSupportFragment
import androidx.leanback.app.VideoSupportFragmentGlueHost
import androidx.leanback.media.MediaPlayerAdapter
import androidx.leanback.media.PlaybackTransportControlGlue
import androidx.leanback.widget.PlaybackControlsRow
import com.xxunghee.androidtvexample.util.CustomGlue

/**
 * Created by xxunghee on 2021-08-27
 *
 * 채널1: Sintel(Blender Foundation) 재생
 */
class Channel1Fragment : VideoSupportFragment() {
    private lateinit var mTransportControlGlue: PlaybackTransportControlGlue<MediaPlayerAdapter>

    private var videoUrl: String? =
        "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Toast.makeText(context, this.javaClass.simpleName, Toast.LENGTH_SHORT).show()

        val glueHost = VideoSupportFragmentGlueHost(this@Channel1Fragment)
        val playerAdapter = MediaPlayerAdapter(context)
        playerAdapter.setRepeatAction(PlaybackControlsRow.RepeatAction.INDEX_NONE)

        mTransportControlGlue = CustomGlue(activity, playerAdapter)
        mTransportControlGlue.host = glueHost
        mTransportControlGlue.playWhenPrepared()

        playerAdapter.setDataSource(Uri.parse(videoUrl))

        hideControlsOverlay(false) // control overlay 비활성화(2)
    }

    override fun showControlsOverlay(runAnimation: Boolean) {}

    override fun onPause() {
        super.onPause()
        mTransportControlGlue.pause()
    }
}