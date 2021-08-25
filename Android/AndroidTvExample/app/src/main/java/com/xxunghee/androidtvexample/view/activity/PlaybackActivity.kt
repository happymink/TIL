package com.xxunghee.androidtvexample.view.activity

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.xxunghee.androidtvexample.view.fragment.PlaybackVideoFragment

/** Loads [PlaybackVideoFragment]. */
class PlaybackActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, PlaybackVideoFragment())
                .commit()
        }
    }
}