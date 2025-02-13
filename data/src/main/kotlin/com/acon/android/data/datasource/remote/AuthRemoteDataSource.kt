package com.acon.android.data.datasource.remote

import com.acon.android.data.dto.request.GoogleTokenRequest
import com.acon.android.data.dto.response.GoogleTokenResponse
import com.acon.android.data.remote.AuthApi
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(
    private val authApi: AuthApi
) {

    suspend fun postLogin(googleLoginRequest: GoogleTokenRequest): GoogleTokenResponse {
        return authApi.postLogin(googleLoginRequest)
    }
}