package com.xxunghee.android.geofencingexample.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "geo_table")
data class GeoEntity(
    @ColumnInfo(name = "date") val date: String,

    @ColumnInfo(name = "time") val time: String,

    @ColumnInfo(name = "status") val status: String,

    @PrimaryKey(autoGenerate = true) val id: Int? = null
)
