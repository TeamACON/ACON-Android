package com.acon.data.repository

import com.acon.data.datasource.remote.TokenRemoteDataSource
import com.acon.domain.repository.GoogleTokenRepository
import javax.inject.Inject

class GoogleTokenRepositoryImpl @Inject constructor(
    private val googleSignInDataSource: TokenRemoteDataSource
): GoogleTokenRepository {
    override suspend fun signIn(): Result<Unit> =
        googleSignInDataSource.signIn()

}