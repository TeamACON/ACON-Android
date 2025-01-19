package com.acon.domain.model.spot

data class SpotDetailInfo(
    val name: String,
    val spotType: String,
    val imageList: List<String>,
    val openStatus: Boolean,
    val address: String,
    val localAcornCount: Int,
    val basicAcornCount: Int,
    val latitude: Double,
    val longitude: Double
)
