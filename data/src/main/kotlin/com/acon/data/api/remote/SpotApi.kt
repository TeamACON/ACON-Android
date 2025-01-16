package com.acon.data.api.remote

import com.acon.data.dto.request.SpotListRequest
import com.acon.data.dto.response.SpotListResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface SpotApi {

    @POST("/api/v1/spots")
    suspend fun fetchSpotList(
        @Body request: SpotListRequest
    ): SpotListResponse
}