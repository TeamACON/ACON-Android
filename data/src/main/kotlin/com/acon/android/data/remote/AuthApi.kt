package com.acon.android.data.remote

import com.acon.android.data.dto.request.GoogleTokenRequest
import com.acon.android.data.dto.response.GoogleTokenResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("/api/v1/auth/login")
    suspend fun postLogin(
        @Body body: GoogleTokenRequest
    ) : GoogleTokenResponse
}