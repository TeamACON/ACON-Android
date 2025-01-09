package com.acon.acon.navigation

import kotlinx.serialization.Serializable

sealed interface Routes {

    @Serializable
    sealed interface SignInRoute : Routes {
        @Serializable
        data object SignIn : SignInRoute
    }

    @Serializable
    sealed interface AreaVerificationRoute : Routes {
        @Serializable
        data object RequireAreaVerification : AreaVerificationRoute

        @Serializable
        data object CheckInMap : AreaVerificationRoute

        @Serializable
        data object Complete : AreaVerificationRoute
    }

    @Serializable
    sealed interface OnboardingRoute : Routes {
        @Serializable
        data object ChooseUnlikeFoods : OnboardingRoute

        @Serializable
        data object ChoosePreferFoods : OnboardingRoute

        @Serializable
        data object ChoosePlace : OnboardingRoute
    }
}
