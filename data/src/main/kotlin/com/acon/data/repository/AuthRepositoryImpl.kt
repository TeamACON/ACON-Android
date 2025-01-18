package com.acon.data.repository

import com.acon.data.datasource.local.TokenLocalDataSource
import com.acon.data.datasource.remote.AuthRemoteDataSource
import com.acon.data.dto.request.GoogleTokenRequest
import com.acon.data.error.runCatchingWith
import com.acon.domain.error.auth.PostLoginError
import com.acon.domain.repository.AuthRepository
import com.acon.domain.type.SocialType
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val tokenLocalDataSource: TokenLocalDataSource,
): AuthRepository {
    override suspend fun postLogin(
        socialType: SocialType,
        idToken: String
    ): Result<Unit> {
        return runCatchingWith(*PostLoginError.createErrorInstances()) {
            val googleLoginResponse = authRemoteDataSource.postLogin(
                GoogleTokenRequest(
                    socialType = socialType,
                    idToken = idToken
                )
            )
            googleLoginResponse.accessToken?.let { tokenLocalDataSource.saveAccessToken(it) }
            googleLoginResponse.refreshToken?.let { tokenLocalDataSource.saveRefreshToken(it) }
        }
    }

}