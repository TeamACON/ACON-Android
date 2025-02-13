package com.acon.android.data.remote

import com.acon.android.data.dto.request.AreaVerificationRequest
import com.acon.android.data.dto.response.AreaVerificationResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AreaVerificationApi {
    @POST("/api/v1/member/area")
    suspend fun verifyArea(
        @Body request: AreaVerificationRequest
    ): AreaVerificationResponse
}
