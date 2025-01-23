package com.acon.domain.repository

import com.acon.domain.model.area.Area

interface AreaVerificationRepository {
   suspend fun verifyArea(
       latitude: Double,
       longitude: Double
   ): Result<Area>
}