package com.acon.android.domain.model.spot

import androidx.compose.runtime.Immutable
import com.acon.android.domain.type.SpotType

@Immutable
data class Spot(
    val id: Long,
    val image: String,
    val matchingRate: Int,
    val type: SpotType,
    val name: String,
    val walkingTime: Int,
)