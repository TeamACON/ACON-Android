package com.acon.acon.navigation.route

import kotlinx.serialization.Serializable

@Serializable
sealed interface OnboardingRoute {

    @Serializable
    data object ChooseUnlikeFoods : OnboardingRoute

    @Serializable
    data object ChoosePreferFoods : OnboardingRoute

    @Serializable
    data object ChoosePlace : OnboardingRoute
}