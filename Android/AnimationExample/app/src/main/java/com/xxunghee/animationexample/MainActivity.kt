package com.xxunghee.animationexample

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView

class MainActivity : AppCompatActivity() {
    private lateinit var container: FragmentContainerView
    private lateinit var layoutButton: ConstraintLayout
    private lateinit var btnWink: Button

    override fun onBackPressed() {
        when(container.visibility) {
            View.VISIBLE -> {
                container.visibility = View.GONE
                layoutButton.visibility = View.VISIBLE
            }
            else -> super.onBackPressed()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        container = findViewById(R.id.container)
        layoutButton = findViewById(R.id.layout_button)
        btnWink = findViewById(R.id.btn_wink)
        btnWink.setOnClickListener {
            changeFragment(WinkFragment())
        }
    }

    private fun changeFragment(fragment: Fragment) {
        container.visibility = View.VISIBLE
        layoutButton.visibility = View.GONE
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }
}