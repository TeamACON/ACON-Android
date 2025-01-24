package com.acon.data.dto.response.upload

import com.acon.domain.model.upload.DotoriCount
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UploadGetDotoriResponse(
    @SerialName("acornCount") val acornCount: Int,
) {
    fun toDotoriCount() = DotoriCount(
        count = acornCount,
    )
}
