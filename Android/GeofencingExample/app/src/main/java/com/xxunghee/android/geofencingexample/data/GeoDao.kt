package com.xxunghee.android.geofencingexample.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GeoDao {
    @Query("SELECT * FROM geo_table")
    fun getAll(): List<GeoEntity>

    @Query("SELECT * FROM geo_table WHERE date >= date('now', '-1 day')")
    fun getFromYesterday(): List<GeoEntity>

    @Insert
    fun insert(geo: GeoEntity)

    @Query("DELETE FROM geo_table")
    fun deleteAll()

    @Query("DELETE FROM geo_table WHERE date < date('now', '-1 day')")
    fun deleteBeforeYesterday()
}