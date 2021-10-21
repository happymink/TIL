package com.xxunghee.android.geofencingexample

import android.Manifest
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingClient
import com.google.android.gms.location.GeofencingRequest
import com.google.android.gms.location.LocationServices
import com.xxunghee.android.geofencingexample.data.GeoDatabase

private const val REQ_ACCESS_FINE_LOCATION = 100
private const val REQ_ACCESS_BACKGROUND_LOCATION = 101

class MainActivity : AppCompatActivity() {
    private lateinit var geofencePendingIntent: PendingIntent
    private lateinit var geofencingClient: GeofencingClient
    private val geofenceList: MutableList<Geofence> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, GeofenceBroadcastReceiver::class.java)
        geofencePendingIntent =
            PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        geofencingClient = LocationServices.getGeofencingClient(this)

        findViewById<RecyclerView>(R.id.rv_db).adapter =
            MainAdapter(GeoDatabase.getDatabase(this)?.geoDao()?.getAll() ?: listOf())

        checkPermission()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            REQ_ACCESS_FINE_LOCATION,
            REQ_ACCESS_BACKGROUND_LOCATION -> {
                grantResults.apply {
                    if (this.isNotEmpty()) {
                        this.forEach {
                            if (it != PackageManager.PERMISSION_GRANTED) {
                                checkPermission()
                                return
                            }
                        }
                    } else {
                        checkPermission()
                    }
                }
            }
        }
    }

    private fun checkPermission() {
        val isPermissionFineLocationGranted =
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED

        val isPermissionBackgroundLocationGranted =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
                ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION
                ) ==
                        PackageManager.PERMISSION_GRANTED
            else true


        when {
            !isPermissionFineLocationGranted -> {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    REQ_ACCESS_FINE_LOCATION
                )
            }
            !isPermissionBackgroundLocationGranted -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.ACCESS_BACKGROUND_LOCATION),
                        REQ_ACCESS_BACKGROUND_LOCATION
                    )
            }
            else -> {
                addGeofenceList("회사", 37.4138903, 127.0995103)
            }
        }
    }

    private fun addGeofenceList(reqId: String, latitude: Double, longitude: Double) {
        geofenceList.add(
            Geofence.Builder().setRequestId(reqId)
                .setCircularRegion(latitude, longitude, Constants.GEOFENCE_RADIUS_IN_METERS)
                .setExpirationDuration(Geofence.NEVER_EXPIRE)
                .setLoiteringDelay(60000)
                .setTransitionTypes(
                    Geofence.GEOFENCE_TRANSITION_ENTER
                            or Geofence.GEOFENCE_TRANSITION_EXIT
                            or Geofence.GEOFENCE_TRANSITION_DWELL
                )
                .build()
        )

        addGeofences()
    }

    private fun addGeofences() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED
        ) return

        geofencingClient.addGeofences(getGeofenceRequest(), geofencePendingIntent).run {
            addOnSuccessListener {
                Log.e("###", "addGeofences: Success")
            }
            addOnFailureListener {
                Log.e("###", "addGeofences: Failure")
            }
        }
    }

    private fun getGeofenceRequest(): GeofencingRequest {
        return GeofencingRequest.Builder().apply {
            addGeofences(geofenceList)
        }.build()
    }
}