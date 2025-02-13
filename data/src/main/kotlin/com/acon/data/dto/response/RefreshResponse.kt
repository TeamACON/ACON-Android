package com.acon.data.dto.response

import com.acon.domain.model.auth.RefreshToken
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RefreshResponse(
    @SerialName("accessToken") val accessToken: String?,
    @SerialName("refreshToken") val refreshToken: String?,
) {
    fun toRefreshToken() = RefreshToken(
        accessToken = accessToken,
        refreshToken = refreshToken,
    )
}