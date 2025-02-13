package com.acon.android.feature.profile.composable

import kotlinx.serialization.Serializable

interface ProfileRoute {

    @Serializable
    data object Graph : ProfileRoute

    @Serializable
    data object Profile : ProfileRoute
}