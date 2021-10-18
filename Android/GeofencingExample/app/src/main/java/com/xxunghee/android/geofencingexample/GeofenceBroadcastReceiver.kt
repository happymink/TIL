package com.xxunghee.android.geofencingexample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofenceStatusCodes
import com.google.android.gms.location.GeofencingEvent
import com.xxunghee.android.geofencingexample.data.GeoDatabase
import com.xxunghee.android.geofencingexample.data.GeoEntity
import com.xxunghee.android.geofencingexample.data.getDate
import com.xxunghee.android.geofencingexample.data.getTime
import java.util.*

class GeofenceBroadcastReceiver : BroadcastReceiver() {
    private var db: GeoDatabase? = null

    override fun onReceive(context: Context?, intent: Intent?) {
        val geofencingEvent = GeofencingEvent.fromIntent(intent)
        if (geofencingEvent.hasError()) {
            val errorMessage = GeofenceStatusCodes
                .getStatusCodeString(geofencingEvent.errorCode)
            Log.e("###", errorMessage)
            return
        }

        when (val geofenceTransition = geofencingEvent.geofenceTransition) {
            Geofence.GEOFENCE_TRANSITION_ENTER,
            Geofence.GEOFENCE_TRANSITION_EXIT -> {
                val triggeringGeofences = geofencingEvent.triggeringGeofences

                val status = when (geofenceTransition) {
                    Geofence.GEOFENCE_TRANSITION_ENTER -> "출근"
                    Geofence.GEOFENCE_TRANSITION_EXIT -> "퇴근"
                    else -> "-"
                }
                triggeringGeofences.forEach {
                    Toast.makeText(context, "${it.requestId} - $status", Toast.LENGTH_LONG).show()
                    GeoDatabase.getDatabase(context)?.geoDao()?.insert(
                        GeoEntity(
                            Locale.getDefault().getDate(),
                            Locale.getDefault().getTime(),
                            status
                        )
                    )
                }
            }
            else -> {
                Toast.makeText(context, "Unknown", Toast.LENGTH_LONG).show()
            }
        }
    }
}