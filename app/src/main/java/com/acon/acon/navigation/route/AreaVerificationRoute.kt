package com.acon.acon.navigation.route

import kotlinx.serialization.Serializable

@Serializable
sealed interface AreaVerificationRoute {

    @Serializable
    data object Graph: AreaVerificationRoute

    @Serializable
    data object RequireAreaVerification : AreaVerificationRoute

    @Serializable
    data object CheckInMap : AreaVerificationRoute

    @Serializable
    data object Complete : AreaVerificationRoute
}