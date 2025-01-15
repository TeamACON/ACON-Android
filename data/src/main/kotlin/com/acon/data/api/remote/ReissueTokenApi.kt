package com.acon.data.api.remote

import com.acon.data.dto.request.RefreshRequest
import com.acon.data.dto.response.RefreshResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ReissueTokenApi {

    @POST("/api/v1/auth/reissue")
    suspend fun postRefresh(
        @Body refreshRequest: RefreshRequest
    ) : Response<RefreshResponse>
}