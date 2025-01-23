package com.acon.data.dto.response.upload

import com.acon.domain.model.upload.SpotVerification
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UploadGetSpotVerifyResponse(
    @SerialName("success")
    val success: Boolean
) {
    fun toSpotVerification() = SpotVerification(
        success = success
    )
}
