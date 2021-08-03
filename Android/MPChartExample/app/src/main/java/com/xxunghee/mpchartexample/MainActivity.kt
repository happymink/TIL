package com.xxunghee.mpchartexample

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.xxunghee.mpchartexample.databinding.ActivityMainBinding

/**
 * Created by Sunghee Kim on 2021-08-02
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.activity = this

        binding.barChart.initChart()

        binding.btnBarChart.setOnClickListener {
            binding.emptyView.visibility = View.GONE
            binding.lineChart.visibility = View.GONE
            binding.barChart.visibility = View.VISIBLE
            binding.barChart.drawRandomBarChart()
        }

        binding.lineChart.initChart()

        binding.btnLineChart.setOnClickListener {
            binding.emptyView.visibility = View.GONE
            binding.barChart.visibility = View.GONE
            binding.lineChart.visibility = View.VISIBLE
            binding.lineChart.drawRandomLineChart()
        }
    }
}