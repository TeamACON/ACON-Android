package com.acon.android.domain.repository

import com.acon.android.domain.model.area.Area

interface AreaVerificationRepository {
   suspend fun verifyArea(
       latitude: Double,
       longitude: Double
   ): Result<Area>
}