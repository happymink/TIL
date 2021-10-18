package com.xxunghee.android.geofencingexample

import android.location.Location
import android.location.LocationListener

interface LocationListener : LocationListener {
    override fun onLocationChanged(location: Location)
}