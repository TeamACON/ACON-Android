package com.acon.data.repository

import com.acon.data.datasource.local.TokenLocalLocalDataSource
import com.acon.data.error.runCatchingWith
import com.acon.domain.repository.TokenLocalRepository
import javax.inject.Inject

class TokenLocalRepositoryImpl @Inject constructor(
    private val tokenLocalLocalDataSource: TokenLocalLocalDataSource,
): TokenLocalRepository {
    override suspend fun saveAccessToken(accessToken: String): Result<Unit> {
        return runCatchingWith() {
            tokenLocalLocalDataSource.saveAccessToken(accessToken)
        }
    }

    override suspend fun saveRefreshToken(refreshToken: String): Result<Unit> {
        return runCatchingWith() {
            tokenLocalLocalDataSource.saveAccessToken(refreshToken)
        }
    }

    override suspend fun getAccessToken(): Result<String?> {
        return runCatchingWith() {
            tokenLocalLocalDataSource.getAccessToken()
        }
    }

    override suspend fun getRefreshToken(): Result<String?> {
        return runCatchingWith() {
            tokenLocalLocalDataSource.getRefreshToken()
        }
    }

    override suspend fun removeAccessToken(): Result<Unit> {
        return runCatchingWith() {
            tokenLocalLocalDataSource.removeAccessToken()
        }
    }

    override suspend fun removeRefreshToken(): Result<Unit> {
        return runCatchingWith() {
            tokenLocalLocalDataSource.removeRefreshToken()
        }
    }

}