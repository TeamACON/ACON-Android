package com.acon.android.domain.repository

import com.acon.android.domain.model.spot.Condition
import com.acon.android.domain.model.spot.Spot
import com.acon.android.domain.model.spot.SpotDetailInfo
import com.acon.android.domain.model.spot.SpotDetailMenu

interface SpotRepository {

    suspend fun fetchSpotList(
        latitude: Double,
        longitude: Double,
        condition: Condition,
    ): Result<List<Spot>>

    suspend fun fetchRecentNavigationLocation(
        spotId: Long,
    ): Result<Unit>

    suspend fun getSpotDetailInfo(
         spotId: Long,
    ): Result<SpotDetailInfo>

    suspend fun getSpotMenuList(
        spotId: Long,
    ): Result<List<SpotDetailMenu>>

}