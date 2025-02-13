package com.acon.android.data.dto.request

import com.acon.android.domain.type.SocialType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GoogleTokenRequest(
    @SerialName("socialType") val socialType: SocialType?,
    @SerialName("idToken") val idToken: String?,
)
