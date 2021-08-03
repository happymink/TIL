package com.xxunghee.mpchartexample.custom

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.LimitLine
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlin.random.Random

/**
 * Created by xxunghee on 2021-08-03
 */
class CustomLineChart : LineChart {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    /**
     * LineChart 초기 설정
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
            position = XAxis.XAxisPosition.BOTTOM   // x축 위치 설정 - 그래프의 아래쪽
            axisMinimum = 0f                        // x축 최소 표현값 설정
            axisMaximum = 9f                        // x축 최대 표현값 설정
            granularity = 1f                        // x축 간격 설정
            labelCount = 10                         // x축에 표현할 라벨 수 설정
        }
    }

    /**
     * y축 설정
     */
    private fun setYAxis() {
        axisRight.isEnabled = false     // 오른쪽 y축 비활성화

        axisLeft.run {
            axisMinimum = 0f        // y축 회소 표현값 설정
            axisMaximum = 1000f     // y축 최대 표현값 설정

            addLimitLine(getCustomLimitLine(500f))  // limit line 설정
            setDrawGridLines(false)                       // y축 격자 표현 비활성화
        }
    }

    /**
     * LimitLine 생성
     *
     * @return dashed limitLine ( - - - - )
     * */
    private fun getCustomLimitLine(limit: Float): LimitLine {
        val limitLine = LimitLine(limit, "500")
        limitLine.lineWidth = 2f                                             // 두께 설정
        limitLine.lineColor = Color.parseColor("#DB3F3F")          // 색 설정
        limitLine.enableDashedLine(10f, 20f, 0f)  // dash 설정(선, 빈칸, offset)

        return limitLine
    }

    /**
     * LineChart 그리기
     *
     * 랜덤 데이터로 차트 그리기
     */
    fun drawRandomLineChart() {
        val lineDataSet = LineDataSet(getRandomLineEntries(), "")
        lineDataSet.axisDependency = YAxis.AxisDependency.LEFT
        lineDataSet.color = Color.parseColor("#008000")         // 선 색 설정
        lineDataSet.lineWidth = 3f                                        // 선 두께 설정
        lineDataSet.setCircleColor(Color.parseColor("#008000")) // 원 색 설정
        lineDataSet.circleRadius = 5f                                     // 원 크기 설정
        lineDataSet.valueTextColor = Color.parseColor("#000000")// 값 표현 텍스트 색 설정
        lineDataSet.valueTextSize = 12f                                   // 값 표현 텍스트 크기 설정
        lineDataSet.isHighlightEnabled = false                            // 하이라이트 비활성화

        val lineData = LineData()           // 차트에 표현할 data object
        lineData.addDataSet(lineDataSet)    // lineData에 dataSet 설정
        lineData.notifyDataChanged()        // data 변경 알림

        this.data = lineData                // chart data 설정
        this.animateX(500)      // x축 애니메이션 활성화
    }

    /**
     * 랜덤 값 가져오기
     *
     * @return entries
     */
    private fun getRandomLineEntries(): List<Entry> {
        val entries = arrayListOf<Entry>()

        for(i in 0..9) {
            entries.add(Entry(i.toFloat(), Random.nextInt(0, 1000).toFloat()))
        }

        return entries
    }
}