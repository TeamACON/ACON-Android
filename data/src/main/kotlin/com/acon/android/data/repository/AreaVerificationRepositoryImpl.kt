package com.acon.android.data.repository

import com.acon.android.data.datasource.remote.AreaVerificationRemoteDataSource
import com.acon.android.data.dto.response.AreaVerificationResponse
import com.acon.android.data.error.runCatchingWith
import com.acon.android.domain.model.area.Area
import com.acon.android.domain.repository.AreaVerificationRepository
import javax.inject.Inject

class AreaVerificationRepositoryImpl @Inject constructor(
   private val remoteDataSource: AreaVerificationRemoteDataSource
) : AreaVerificationRepository {

   override suspend fun verifyArea(
       latitude: Double,
       longitude: Double
   ): Result<Area> = runCatchingWith {
       remoteDataSource.verifyArea(
           latitude = latitude,
           longitude = longitude
       ).toArea()
   }
}

private fun AreaVerificationResponse.toArea() = Area(
   areaName = area
)