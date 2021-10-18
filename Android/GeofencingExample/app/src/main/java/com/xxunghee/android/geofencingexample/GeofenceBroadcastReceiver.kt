package com.xxunghee.android.geofencingexample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofenceStatusCodes
import com.google.android.gms.location.GeofencingEvent

class GeofenceBroadcastReceiver : BroadcastReceiver() {
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

                val transitionMsg = when (geofenceTransition) {
                    Geofence.GEOFENCE_TRANSITION_ENTER -> "Enter"
                    Geofence.GEOFENCE_TRANSITION_EXIT -> "Exit"
                    else -> "-"
                }
                triggeringGeofences.forEach {
                    Toast.makeText(context, "${it.requestId} - $transitionMsg", Toast.LENGTH_LONG)
                        .show()
                }
            }
            else -> {
                Toast.makeText(context, "Unknown", Toast.LENGTH_LONG).show()
            }
        }
    }
}