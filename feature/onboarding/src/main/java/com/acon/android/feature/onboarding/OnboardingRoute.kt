package com.acon.android.feature.onboarding

import kotlinx.serialization.Serializable

sealed interface OnboardingRoute {

    @Serializable
    data object Graph : OnboardingRoute

    @Serializable
    data object OnboardingScreen : OnboardingRoute

    @Serializable
    data object LastLoading : OnboardingRoute
}