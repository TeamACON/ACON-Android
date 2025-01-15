package com.acon.domain.model.auth

data class GoogleToken(
    val accessToken: String,
    val refreshToken: String,
)
