package com.acon.android.feature.areaverification

sealed interface AreaVerificationSideEffect {
    data class NavigateToSettings(val packageName: String) : AreaVerificationSideEffect
    data class NavigateToNextScreen(val latitude: Double, val longitude: Double) :
        AreaVerificationSideEffect
    data class NavigateToNewArea(val latitude: Double, val longitude: Double) :
        AreaVerificationSideEffect
}
