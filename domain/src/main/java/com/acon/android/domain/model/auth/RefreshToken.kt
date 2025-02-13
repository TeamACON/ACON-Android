package com.acon.android.domain.model.auth

data class RefreshToken(
    val accessToken: String?,
    val refreshToken: String?,
)
