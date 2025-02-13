package com.acon.android.data.datasource.remote

import com.acon.android.data.dto.request.AreaVerificationRequest
import com.acon.android.data.dto.response.AreaVerificationResponse
import com.acon.android.data.remote.AreaVerificationApi
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
