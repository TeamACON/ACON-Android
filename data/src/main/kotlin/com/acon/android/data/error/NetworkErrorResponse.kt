package com.acon.android.data.error

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkErrorResponse(
    @SerialName("code") val code: Int,
    @SerialName("message") val message: String
)