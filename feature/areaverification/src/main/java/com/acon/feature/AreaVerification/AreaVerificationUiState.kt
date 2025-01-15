package com.acon.feature.areaverification

data class AreaVerificationUiState(
    val isNewLocationSelected: Boolean = false,
    val isButtonEnabled: Boolean = true,
    val isLocationPermissionGranted: Boolean = false,
    val showPermissionDialog: Boolean = false,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val isLocationObtained: Boolean = false
)
