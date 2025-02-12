package com.acon.domain.repository

interface SocialRepository {
    suspend fun signIn(): Result<Unit>
}