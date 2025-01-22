package com.acon.data.repository

import com.acon.data.datasource.local.TokenLocalDataSource
import com.acon.data.error.runCatchingWith
import com.acon.domain.repository.TokenRepository
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val tokenLocalDataSource: TokenLocalDataSource,
): TokenRepository {
    override suspend fun saveAccessToken(accessToken: String): Result<Unit> {
        return runCatchingWith() {
            tokenLocalDataSource.saveAccessToken(accessToken)
        }
    }

    override suspend fun saveRefreshToken(refreshToken: String): Result<Unit> {
        return runCatchingWith() {
            tokenLocalDataSource.saveAccessToken(refreshToken)
        }
    }

    override suspend fun getAccessToken(): Result<String?> {
        return runCatchingWith() {
            tokenLocalDataSource.getAccessToken()
        }
    }

    override suspend fun getRefreshToken(): Result<String?> {
        return runCatchingWith() {
            tokenLocalDataSource.getRefreshToken()
        }
    }

    override suspend fun removeAccessToken(): Result<Unit> {
        return runCatchingWith() {
            tokenLocalDataSource.removeAccessToken()
        }
    }

    override suspend fun removeRefreshToken(): Result<Unit> {
        return runCatchingWith() {
            tokenLocalDataSource.removeRefreshToken()
        }
    }

}