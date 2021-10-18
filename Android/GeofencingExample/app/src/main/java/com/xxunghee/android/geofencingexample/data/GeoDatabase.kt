package com.xxunghee.android.geofencingexample.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [GeoEntity::class], version = 1)
abstract class GeoDatabase : RoomDatabase() {
    abstract fun geoDao(): GeoDao

    companion object {
        var instance: GeoDatabase? = null

        fun getDatabase(context: Context?): GeoDatabase? {
            if (instance == null) {
                synchronized(GeoDatabase::class) {
                    instance = context?.let {
                        Room.databaseBuilder(
                            it.applicationContext,
                            GeoDatabase::class.java,
                            "geo_db"
                        ).allowMainThreadQueries().build()
                    }
                }
            }

            return instance
        }

        fun deleteDatabase() {
            instance = null
        }
    }
}