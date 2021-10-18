package com.xxunghee.android.geofencingexample.data

import java.text.SimpleDateFormat
import java.util.*

fun Locale.getDate(): String {
    return SimpleDateFormat("yyyy-MM-dd", this).format(Date())
}

fun Locale.getTime(): String {
    return SimpleDateFormat("HH:mm", this).format(Date())
}