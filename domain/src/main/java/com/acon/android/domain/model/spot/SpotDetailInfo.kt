package com.acon.android.domain.model.spot

import androidx.compose.runtime.Immutable
import com.acon.android.domain.type.SpotType

@Immutable
data class SpotDetailInfo(
    val id: Long,
    val name: String,
    val spotType: SpotType,
    val imageList: List<String>,
    val openStatus: Boolean,
    val address: String,
    val localAcornCount: Int,
    val basicAcornCount: Int,
    val latitude: Double,
    val longitude: Double
)
