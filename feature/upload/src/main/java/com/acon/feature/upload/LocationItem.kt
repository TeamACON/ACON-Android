package com.acon.feature.upload

data class LocationItem(
    val spotId: Int,
    val name: String,
    val address: String,
    val spotType: SpotType
)

enum class SpotType {
    CAFE, RESTAURANT
}
