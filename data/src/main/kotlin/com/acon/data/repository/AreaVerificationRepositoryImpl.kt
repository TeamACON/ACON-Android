package com.acon.data.repository

import com.acon.data.datasource.remote.AreaVerificationRemoteDataSource
import com.acon.data.dto.response.AreaVerificationResponse
import com.acon.data.error.runCatchingWith
import com.acon.domain.model.area.Area
import com.acon.domain.repository.AreaVerificationRepository
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