package com.acon.domain.repository

interface GoogleTokenRepository {
    suspend fun signIn(): Result<Unit>
}