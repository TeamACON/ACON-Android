package com.acon.data.datasource.remote

import com.acon.data.api.remote.SpotApi
import com.acon.data.dto.request.SpotListRequest
import com.acon.data.dto.response.SpotListResponse
import javax.inject.Inject

class SpotRemoteDataSource @Inject constructor(
    private val spotApi: SpotApi
) {

    suspend fun fetchSpotList(request: SpotListRequest): SpotListResponse {
        return spotApi.fetchSpotList(request)
    }
}