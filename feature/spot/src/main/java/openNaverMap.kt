package com.acon.feature.spot

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri

internal fun openNaverMap(
    context: Context,
    location: Location,
    destinationLat: Double,
    destinationLng: Double,
    destinationName: String
) {
    val url = "nmap://route/walk?slat=${location.latitude}&slng=${location.longitude}&sname=내 위치&" +
            "dlat=$destinationLat&dlng=$destinationLng&dname=$destinationName&" +
            "appname=com.acon.acon"

    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
        addCategory(Intent.CATEGORY_BROWSABLE)
    }

    val packageManager = context.packageManager
    val resolveInfoList = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)

    if (resolveInfoList.isEmpty()) {
        val playStoreIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("market://details?id=com.nhn.android.nmap")
        )
        context.startActivity(playStoreIntent)
    } else {
        context.startActivity(intent)
    }
}