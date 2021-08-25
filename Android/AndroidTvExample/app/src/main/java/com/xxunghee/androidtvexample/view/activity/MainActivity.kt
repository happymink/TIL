package com.xxunghee.androidtvexample.view.activity

import android.os.Bundle
import android.view.KeyEvent
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.xxunghee.androidtvexample.view.fragment.MainFragment
import com.xxunghee.androidtvexample.R

/**
 * Loads [MainFragment].
 */
class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_browse_fragment, MainFragment())
                .commitNow()
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        Toast.makeText(this, "$keyCode", Toast.LENGTH_SHORT).show()

        return super.onKeyDown(keyCode, event)
    }
}