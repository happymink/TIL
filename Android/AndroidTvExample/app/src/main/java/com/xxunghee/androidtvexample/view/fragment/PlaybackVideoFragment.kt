package com.xxunghee.androidtvexample.view.fragment

import android.net.Uri
import android.os.Bundle
import androidx.leanback.app.VideoSupportFragment
import androidx.leanback.app.VideoSupportFragmentGlueHost
import androidx.leanback.media.MediaPlayerAdapter
import androidx.leanback.media.PlaybackTransportControlGlue
import androidx.leanback.widget.PlaybackControlsRow
import com.xxunghee.androidtvexample.util.CustomGlue
import com.xxunghee.androidtvexample.data.Movie
import com.xxunghee.androidtvexample.view.activity.DetailsActivity

/**
 *  Handles video playback with media controls.
 *
 *  Modified by xxunghee on 2021-08-16
 * */
class PlaybackVideoFragment : VideoSupportFragment() {

    private lateinit var mTransportControlGlue: PlaybackTransportControlGlue<MediaPlayerAdapter>

    private var title: String? = "시작 비디오"
    private var description: String? = "시작 비디오 설명"
    private var videoUrl: String? = "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review.mp4"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity?.intent?.getSerializableExtra(DetailsActivity.MOVIE) as? Movie)?.let {
            title = it.title
            description = it.description
            videoUrl = it.videoUrl
        }

        val glueHost = VideoSupportFragmentGlueHost(this@PlaybackVideoFragment)
        val playerAdapter = MediaPlayerAdapter(context)
        playerAdapter.setRepeatAction(PlaybackControlsRow.RepeatAction.INDEX_NONE)

        mTransportControlGlue = CustomGlue(activity, playerAdapter)
        mTransportControlGlue.host = glueHost
        mTransportControlGlue.title = title
        mTransportControlGlue.subtitle = description
        mTransportControlGlue.playWhenPrepared()

        playerAdapter.setDataSource(Uri.parse(videoUrl))

        hideControlsOverlay(false) // control overlay 비활성화(2)
    }

    /**
     * [참조](http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review.mp4)
     */
    override fun showControlsOverlay(runAnimation: Boolean) { } // control overlay 비활성화(1)

    override fun onPause() {
        super.onPause()
        mTransportControlGlue.pause()
    }
}