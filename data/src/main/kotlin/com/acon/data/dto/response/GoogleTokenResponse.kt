package com.acon.data.dto.response

import com.acon.domain.model.auth.GoogleToken
import com.acon.domain.model.spot.Spot
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GoogleTokenResponse(
    @SerialName("accessToken") val accessToken: String,
    @SerialName("refreshToken") val refreshToken: String,
) {
    fun toGoogleToken() = GoogleToken(
        accessToken = accessToken,
        refreshToken = refreshToken,
    )
}
