package com.acon.domain.repository

import com.acon.domain.model.spot.Condition
import com.acon.domain.model.spot.Spot

interface SpotRepository {

    suspend fun fetchSpotList(
        latitude: Double,
        longitude: Double,
        condition: Condition,
        walkingTime: Int,
        priceRange: Int
    ): Result<List<Spot>>
}