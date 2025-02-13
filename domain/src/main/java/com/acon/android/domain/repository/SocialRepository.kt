package com.acon.android.domain.repository

interface SocialRepository {
    suspend fun signIn(): Result<Unit>
}