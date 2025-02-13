package com.acon.android.data.dto.response

import com.acon.android.domain.model.auth.RefreshToken
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