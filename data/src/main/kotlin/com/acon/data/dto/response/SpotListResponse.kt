package com.acon.data.dto.response

import com.acon.domain.type.SpotType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpotListResponse(
    @SerialName("spotList") val spotList: List<SpotResponse>
)

@Serializable
data class SpotResponse(
    @SerialName("id") val id: Int,
    @SerialName("image") val image: String,
    @SerialName("matchingRate") val matchingRate: Int,
    @SerialName("type") val type: SpotType,
    @SerialName("name") val name: String,
    @SerialName("walkingTime") val walkingTime: Int,
)