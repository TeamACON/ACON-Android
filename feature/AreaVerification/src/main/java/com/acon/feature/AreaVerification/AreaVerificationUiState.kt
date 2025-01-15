package com.acon.feature.AreaVerification

data class AreaVerificationUiState(
    val isNewLocationSelected: Boolean = false,
    val isButtonEnabled: Boolean = true,
    val isLocationPermissionGranted: Boolean = false,
    val showPermissionDialog: Boolean = false
)