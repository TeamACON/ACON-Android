package com.acon.feature.profile

import kotlinx.serialization.Serializable

interface ProfileRoute {

    @Serializable
    data object Graph : ProfileRoute

    @Serializable
    data object Profile : ProfileRoute
}