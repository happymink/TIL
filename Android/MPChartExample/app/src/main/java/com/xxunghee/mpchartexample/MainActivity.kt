package com.xxunghee.mpchartexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.xxunghee.mpchartexample.custom.CustomBarChart

/**
 * Created by Sunghee Kim on 2021-08-02
 */
class MainActivity : AppCompatActivity() {
    private var barChartButton: Button? = null
    private var barChart: CustomBarChart? = null
    private var emptyView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        emptyView = findViewById(R.id.empty_view)

        barChart = findViewById(R.id.bar_chart)
        barChart?.initChart()

        barChartButton = findViewById(R.id.btn_bar_chart)
        barChartButton?.setOnClickListener {
            emptyView?.visibility = View.GONE
            barChart?.visibility = View.VISIBLE
            barChart?.drawRandomBarChart()
        }
    }
}