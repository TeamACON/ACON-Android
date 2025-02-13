package com.acon.android.data.remote

import com.acon.android.data.dto.request.RefreshRequest
import com.acon.android.data.dto.response.RefreshResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ReissueTokenApi {

    @POST("/api/v1/auth/reissue")
    suspend fun postRefresh(
        @Body refreshRequest: RefreshRequest
    ) : RefreshResponse
}