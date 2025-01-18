package com.acon.data.api.remote

import com.acon.data.dto.request.GoogleTokenRequest
import com.acon.data.dto.response.GoogleTokenResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("/api/v1/auth/login")
    suspend fun postLogin(
        @Body body: GoogleTokenRequest
    ) : GoogleTokenResponse
}