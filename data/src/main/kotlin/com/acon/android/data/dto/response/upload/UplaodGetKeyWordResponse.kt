package com.acon.android.data.dto.response.upload

import com.acon.android.domain.model.upload.KeyWord
import com.acon.android.domain.model.upload.SpotListItem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UploadGetKeyWordResponse(
    @SerialName("spotList")
    val spotList: List<SpotListItemDto>
) {
    fun toKeyWord() = KeyWord(
        spotList = spotList.map { it.toSpotListItem() }
    )
}

@Serializable
data class SpotListItemDto(
    @SerialName("spotId")
    val spotId: Long,
    @SerialName("name")
    val name: String,
    @SerialName("address")
    val address: String,
    @SerialName("spotType")
    val spotType: String
) {
    fun toSpotListItem() = SpotListItem(
        spotId = spotId,
        name = name,
        address = address,
        spotType = spotType
    )
}
