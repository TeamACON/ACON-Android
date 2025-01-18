package com.acon.domain.repository

import com.acon.domain.type.SocialType

interface AuthRepository {
    suspend fun postLogin(socialType: SocialType, idToken: String): Result<Unit>
}