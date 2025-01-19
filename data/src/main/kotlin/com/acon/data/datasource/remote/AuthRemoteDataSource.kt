package com.acon.data.datasource.remote

import com.acon.data.api.remote.AuthApi
import com.acon.data.dto.request.GoogleTokenRequest
import com.acon.data.dto.response.GoogleTokenResponse
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(
    private val authApi: AuthApi
) {

    suspend fun postLogin(googleLoginRequest: GoogleTokenRequest): GoogleTokenResponse {
        return authApi.postLogin(googleLoginRequest)
    }
}