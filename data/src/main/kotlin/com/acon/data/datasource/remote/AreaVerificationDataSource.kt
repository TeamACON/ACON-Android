package com.acon.data.datasource.remote

import com.acon.data.api.remote.AreaVerificationApi
import com.acon.data.dto.request.AreaVerificationRequest
import com.acon.data.dto.response.AreaVerificationResponse
import javax.inject.Inject

class AreaVerificationRemoteDataSource @Inject constructor(
    private val areaVerificationApi: AreaVerificationApi,
) {
    suspend fun verifyArea(
        latitude: Double,
        longitude: Double
    ): AreaVerificationResponse {
        return areaVerificationApi.verifyArea(
            request = AreaVerificationRequest(
                latitude = latitude,
                longitude = longitude
            )
        )
    }
}
