package com.acon.feature.upload

data class UploadState(
    val selectedLocation: LocationItem? = null,
    val currentStep: UploadStep = UploadStep.LOCATION_SELECTION,
    val selectedCount: Int = 0,
    val maxCount: Int = 5,
    val ownedDotoriCount: Int = 25, //from server
    val isButtonEnabled: Boolean = false,
    val animatingIndex: Int? = null,
    val isLocationSearchVisible: Boolean = false
)
