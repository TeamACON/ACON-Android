package com.acon.android.navigation.route

import kotlinx.serialization.Serializable

@Serializable
sealed interface SplashRoute {

    @Serializable
    data object Graph: SplashRoute

    @Serializable
    data object Splash : SplashRoute
}