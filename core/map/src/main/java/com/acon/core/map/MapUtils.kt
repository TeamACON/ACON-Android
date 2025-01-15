package com.acon.core.map

import android.annotation.SuppressLint
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
        locationProviderClient.lastLocation.addOnSuccessListener { location ->
            location?.let {
                onReady(it)
            }
        }
    }
}
