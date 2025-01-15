package com.acon.feature.AreaVerification

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.acon.core.utils.feature.permission.CheckAndRequestLocationPermission
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.Marker

@SuppressLint("MissingPermission")
private fun getLastLocation(
    context: Context,
    fusedLocationClient: FusedLocationProviderClient,
    onLocationResult: (Location?) -> Unit
) {
    fusedLocationClient.lastLocation
        .addOnSuccessListener { location ->
            onLocationResult(location)
        }
        .addOnFailureListener {
            onLocationResult(null)
        }
}

@Composable
fun LocationMapScreen(
    onLocationObtained: (Double, Double) -> Unit,
    initialLatitude: Double = 0.0,
    initialLongitude: Double = 0.0,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var naverMap: NaverMap? by remember { mutableStateOf(null) }
    var currentLocation by remember { mutableStateOf<Location?>(null) }

    val fusedLocationClient: FusedLocationProviderClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }

    if (initialLatitude != 0.0 && initialLongitude != 0.0) {
        naverMap?.let { map ->
            val cameraUpdate = CameraUpdate.scrollTo(LatLng(initialLatitude, initialLongitude))
            map.moveCamera(cameraUpdate)
            Marker().apply {
                position = LatLng(initialLatitude, initialLongitude)
                this.map = map
            }
        }
    } else {
        CheckAndRequestLocationPermission {
            getLastLocation(context, fusedLocationClient) { location ->
                currentLocation = location
                location?.let {
                    onLocationObtained(it.latitude, it.longitude)
                    naverMap?.let { map ->
                        val cameraUpdate = CameraUpdate.scrollTo(LatLng(it.latitude, it.longitude))
                        map.moveCamera(cameraUpdate)
                        Marker().apply {
                            position = LatLng(it.latitude, it.longitude)
                            this.map = map
                        }
                    }
                }
            }
        }
    }

    Box(modifier = modifier) {
        AndroidView(
            factory = { context ->
                MapView(context).apply {
                    getMapAsync { map ->
                        naverMap = map
                        map.uiSettings.apply {
                            isZoomControlEnabled = true
                            isCompassEnabled = true
                        }

                        // 초기 위치 설정
                        if (initialLatitude != 0.0 && initialLongitude != 0.0) {
                            val cameraUpdate =
                                CameraUpdate.scrollTo(LatLng(initialLatitude, initialLongitude))
                            map.moveCamera(cameraUpdate)
                            Marker().apply {
                                position = LatLng(initialLatitude, initialLongitude)
                                this.map = map
                            }
                        } else {
                            currentLocation?.let { location ->
                                val cameraUpdate = CameraUpdate.scrollTo(
                                    LatLng(
                                        location.latitude,
                                        location.longitude
                                    )
                                )
                                map.moveCamera(cameraUpdate)
                                Marker().apply {
                                    position = LatLng(location.latitude, location.longitude)
                                    this.map = map
                                }
                            }
                        }
                    }
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}
