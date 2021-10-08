package com.xxunghee.animationexample

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.ImageView

const val DELAY_TIME = 100L

class WinkFragment : Fragment() {
    private var ivWink: ImageView? = null

    private val images = listOf(R.drawable.img_wink_1, R.drawable.img_wink_2, R.drawable.img_wink_3)
    private var position = 0

    private val mHandler = Handler(Looper.getMainLooper())

    private val changeImageRunnable: Runnable = object : Runnable {
        override fun run() {
            if (position >= images.size) {
                position = 0
            }

            ivWink?.setImageResource(images[position ++])

            mHandler.postDelayed(this, DELAY_TIME)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_wink, container, false)

        ivWink = root.findViewById(R.id.iv_wink)
        changeImageRunnable.run()

        return root
    }

    override fun onPause() {
        super.onPause()
        mHandler.removeCallbacks(changeImageRunnable)
    }
}