package com.xxunghee.android.geofencingexample

import android.Manifest
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingClient
import com.google.android.gms.location.GeofencingRequest
import com.google.android.gms.location.LocationServices

private const val REQ_ACCESS_FINE_LOCATION = 100
private const val REQ_ACCESS_BACKGROUND_LOCATION = 101

class MainActivity : AppCompatActivity(), LocationListener {
    private lateinit var tvCurLatitude: TextView
    private lateinit var tvCurLongitude: TextView
    private lateinit var tvStatus: TextView

    private lateinit var geofencePendingIntent: PendingIntent
    private lateinit var geofencingClient: GeofencingClient
    private val geofenceList: MutableList<Geofence> = mutableListOf()

    private lateinit var locationManager: LocationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvCurLatitude = findViewById(R.id.tv_cur_latitude)
        tvCurLongitude = findViewById(R.id.tv_cur_longitude)
        tvStatus = findViewById(R.id.tv_status)

        val intent = Intent(this, GeofenceBroadcastReceiver::class.java)
        geofencePendingIntent =
            PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        geofencingClient = LocationServices.getGeofencingClient(this)

        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, this)
        val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        location?.let {
            tvCurLatitude.text = it.latitude.toString()
            tvCurLongitude.text = it.longitude.toString()
        }

        checkPermission()
    }

    override fun onLocationChanged(location: Location) {
        tvCurLatitude.text = location.latitude.toString()
        tvCurLongitude.text = location.longitude.toString()
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
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) ==
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
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_BACKGROUND_LOCATION),
                    REQ_ACCESS_BACKGROUND_LOCATION
                )
            }
            else -> {
                addGeofenceList("회사", 37.413252, 127.099763)
            }
        }
    }

    private fun addGeofenceList(reqId: String, latitude: Double, longitude: Double) {
        geofenceList.add(
            Geofence.Builder().setRequestId(reqId)
                .setCircularRegion(latitude, longitude, Constants.GEOFENCE_RADIUS_IN_METERS)
                .setExpirationDuration(Geofence.NEVER_EXPIRE)
                .setLoiteringDelay(300000) // DWELL 판정 기준: 5분
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
            setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
            addGeofences(geofenceList)
        }.build()
    }
}