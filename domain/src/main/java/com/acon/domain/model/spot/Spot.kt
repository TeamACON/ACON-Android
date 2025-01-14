package com.acon.domain.model.spot

import androidx.compose.runtime.Immutable
import com.acon.domain.type.SpotType

@Immutable
data class Spot(
    val id: Int,
    val image: String,
    val matchingRate: Int,
    val type: SpotType,
    val name: String,
    val walkingTime: Int,
)