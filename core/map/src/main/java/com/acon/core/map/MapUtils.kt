package com.acon.core.map

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.acon.core.utils.feature.permission.CheckAndRequestLocationPermission
import com.google.android.gms.location.LocationServices

@Composable
@SuppressLint("MissingPermission")
fun ProceedWithLocation(onReady: (Location) -> Unit) {
    val context = LocalContext.current
    val locationProviderClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }

    CheckAndRequestLocationPermission {
        locationProviderClient.lastLocation.addOnSuccessListener {
            onReady(it)
        }
    }
}

fun Context.onLocationReady(onReady: (Location) -> Unit) {
    val locationProviderClient = LocationServices.getFusedLocationProviderClient(this)

    locationProviderClient.lastLocation.addOnSuccessListener {
        onReady(it)
    }
}