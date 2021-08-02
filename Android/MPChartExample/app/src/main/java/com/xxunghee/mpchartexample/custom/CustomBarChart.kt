package com.xxunghee.mpchartexample.custom

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import kotlin.random.Random

/**
 * Created by xxunghee on 2021-08-02
 */
class CustomBarChart : BarChart {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    /**
     * BarChart 초기 설정
     */
    fun initChart() {
        super.init()

        description.isEnabled = false     // description 비활성화
        legend.isEnabled = false          // legend 비활성화 *legend: 차트의 범례
        isKeepPositionOnRotation = false  // 기기 orientation 시 position 유지하지 않음

        setDrawGridBackground(false)  // 배경 격자 비활성화
        setDrawBorders(false)         // 배경 테두리 비활성화
        setTouchEnabled(false)        // 차트 터치 등의 제스처 비활성화
        setPinchZoom(false)           // 핀치 줌 비활성화

        setXAxis()
        setYAxis()
    }

    /**
     * x축 설정
     */
    private fun setXAxis() {
        xAxis.run {
            position = XAxis.XAxisPosition.BOTTOM                  // x축 위치 설정 - 그래프의 아래쪽
            axisLineColor = Color.parseColor("#000000") // x축 색 설정

            setDrawLabels(false)    // x축 라벨 표현 비활성화
            setDrawGridLines(false) // x축 격자 표현 비활성화
        }
    }

    /**
     * y축 설정
     */
    private fun setYAxis() {
        axisRight.isEnabled = false // 오른쪽 y축 비활성화

        axisLeft.run {
            axisMaximum = 101f  // y축 최대 표현값 설정
            axisMinimum = 0f    // y축 최소 표현값 설정
            granularity = 20f   // y축 간격 설정

            setDrawLabels(true)     // y축 라벨 활성화 (ex. 0, 20, 40, ...)
            setDrawGridLines(true)  // y축 격자 표현 활성화

            gridColor = Color.parseColor("#CBCBCB")     // 격자 색 설정
            axisLineColor = Color.parseColor("#000000") // y축 색 설정
        }
    }

    /**
     * 차트 그리기
     *
     * 랜덤 데이터, 랜덤 색으로 bar chart 그리기
     */
    fun drawRandomBarChart() {
        val barDataSet = BarDataSet(getRandomBarEntries(), "")
        barDataSet.axisDependency = YAxis.AxisDependency.LEFT // 왼쪽
        barDataSet.colors = getRandomColors()                 // bar 색 설정
        barDataSet.isHighlightEnabled = false                 // 하이라이트 비활성화
        barDataSet.setDrawValues(false)                       // 값 표현 비활성화

        val barData = BarData()         // 차트에 표현할 data object
        barData.addDataSet(barDataSet)  // barData에 dataSet 설정
        barData.barWidth = 0.3f         // bar 너비 설정

        this.data = barData            // chart data 설정
        this.animateY(700) // y축 애니메이션 활성화
        this.invalidate()              // 차트 그리기
    }

    /**
     * 랜덤 값 가져오기
     *
     * @return entries
     */
    private fun getRandomBarEntries(): List<BarEntry> {
        val entries = arrayListOf<BarEntry>()

        for(i in 1..5) {
            entries.add(BarEntry(i.toFloat(), Random.nextInt(0, 100).toFloat()))
        }

        return entries
    }

    /**
     * 랜덤 색 리스트 가져오기
     *
     * @return colors
     */
    private fun getRandomColors(): List<Int> {
        val colors = listOf<IntArray>(
            ColorTemplate.VORDIPLOM_COLORS,
            ColorTemplate.COLORFUL_COLORS,
            ColorTemplate.JOYFUL_COLORS,
            ColorTemplate.LIBERTY_COLORS,
            ColorTemplate.MATERIAL_COLORS,
            ColorTemplate.PASTEL_COLORS
        )

        return colors[Random.nextInt(0, 5)].asList()
    }
}