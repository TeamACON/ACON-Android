package com.acon.android.data.dto.response.upload

import com.acon.android.domain.model.upload.DotoriCount
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
