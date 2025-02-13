package com.acon.android.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReviewRequest(
    @SerialName("spotId")
    val spotId: Long,
    @SerialName("acornCount")
    val acornCount: Int
)