package com.acon.data.response

import com.acon.domain.type.SpotType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpotListResponse(
    @SerialName("id") val id: Int,
    @SerialName("image") val image: String,
    @SerialName("matchingRate") val matchingRate: Int,
    @SerialName("type") val type: SpotType,
    @SerialName("name") val name: String,
    @SerialName("walkingTime") val walkingTime: Int,
)