package com.acon.feature.spot.com.acon.feature.spot

import kotlinx.serialization.Serializable

interface SpotRoute {

    @Serializable
    data object Graph : SpotRoute

    @Serializable
    data object SpotList : SpotRoute

    @Serializable
    data class SpotDetail(val id: Int) : SpotRoute
}