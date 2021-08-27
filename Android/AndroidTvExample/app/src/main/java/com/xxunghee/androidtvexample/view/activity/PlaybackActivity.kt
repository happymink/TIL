package com.xxunghee.androidtvexample.view.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.xxunghee.androidtvexample.view.fragment.Channel1Fragment
import com.xxunghee.androidtvexample.view.fragment.Channel2Fragment
import com.xxunghee.androidtvexample.view.fragment.PlaybackVideoFragment

/** Loads [PlaybackVideoFragment]. */
class PlaybackActivity : FragmentActivity() {
    private val channels = listOf<Fragment>(PlaybackVideoFragment(), Channel1Fragment(), Channel2Fragment())
    private var channel = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, PlaybackVideoFragment())
                .commit()
        }
    }

    /**
     * DPAD 키 이벤트 설정
     *
     * up(^), down(v): 채널 변경
     */
    @SuppressLint("RestrictedApi")
    override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
        return if (event?.keyCode == KeyEvent.KEYCODE_DPAD_UP && event.action == KeyEvent.ACTION_DOWN) {
            if (++channel > channels.size) channel -= channels.size
            supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, channels[channel]).commit()
            true
        } else if (event?.keyCode == KeyEvent.KEYCODE_DPAD_DOWN && event.action == KeyEvent.ACTION_DOWN) {
            if (--channel < 0) channel += channels.size
            supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, channels[channel]).commit()
            true
        } else {
            super.dispatchKeyEvent(event)
        }
    }
}