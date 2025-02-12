package com.acon.data.repository

import com.acon.data.datasource.remote.TokenRemoteDataSource
import com.acon.data.error.runCatchingWith
import com.acon.domain.repository.AuthRepository
import com.acon.domain.repository.SocialRepository
import com.acon.domain.type.SocialType
import javax.inject.Inject

class SocialRepositoryImpl @Inject constructor(
    private val tokenRemoteDataSource: TokenRemoteDataSource,
    private val authRepository: AuthRepository
): SocialRepository {
    override suspend fun signIn(): Result<Unit> {
        return runCatchingWith() {
            val idToken = tokenRemoteDataSource.signIn().getOrThrow()
            authRepository.postLogin(
                socialType = SocialType.GOOGLE,
                idToken = idToken
            )
        }
    }
}