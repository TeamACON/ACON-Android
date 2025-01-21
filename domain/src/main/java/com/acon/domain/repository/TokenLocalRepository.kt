package com.acon.domain.repository

interface TokenLocalRepository {
    suspend fun saveAccessToken(accessToken: String): Result<Unit>
    suspend fun saveRefreshToken(refreshToken: String): Result<Unit>
    suspend fun getAccessToken(): Result<String?>
    suspend fun getRefreshToken(): Result<String?>
    suspend fun removeAccessToken(): Result<Unit>
    suspend fun removeRefreshToken(): Result<Unit>
}