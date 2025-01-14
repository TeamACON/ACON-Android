package com.acon.domain.model.spot

import com.acon.domain.type.SpotType

data class Spot(
    val id: Int,
    val image: String,
    val matchingRate: Int,
    val type: SpotType,
    val name: String,
    val walkingTime: Int,
)