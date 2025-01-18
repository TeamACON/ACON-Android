package com.acon.domain.model.auth

data class RefreshToken(
    val accessToken: String?,
    val refreshToken: String?,
)
