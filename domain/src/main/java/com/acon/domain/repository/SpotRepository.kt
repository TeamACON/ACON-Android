package com.acon.domain.repository

import com.acon.domain.model.spot.Condition
import com.acon.domain.model.spot.Spot
import com.acon.domain.model.spot.SpotDetailInfo
import com.acon.domain.model.spot.SpotDetailMenu

interface SpotRepository {

    suspend fun fetchSpotList(
        latitude: Double,
        longitude: Double,
        condition: Condition,
    ): Result<List<Spot>>

    suspend fun getSpotDetailInfo(
         spotId: Long,
    ): Result<SpotDetailInfo>

    suspend fun getSpotMenuList(
        spotId: Int,
    ): Result<List<SpotDetailMenu>>
}