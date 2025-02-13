package com.acon.android.data.di

import com.acon.android.data.repository.AreaVerificationRepositoryImpl
import com.acon.android.data.repository.AuthRepositoryImpl
import com.acon.android.data.repository.MapRepositoryImpl
import com.acon.android.data.repository.OnboardingRepositoryImpl
import com.acon.android.data.repository.SpotRepositoryImpl
import com.acon.android.data.repository.TokenRepositoryImpl
import com.acon.android.data.repository.UploadRepositoryImpl
import com.acon.android.domain.repository.AreaVerificationRepository
import com.acon.android.domain.repository.AuthRepository
import com.acon.android.domain.repository.MapRepository
import com.acon.android.domain.repository.OnboardingRepository
import com.acon.android.domain.repository.SpotRepository
import com.acon.android.domain.repository.TokenRepository
import com.acon.android.domain.repository.UploadRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindsAuthRepository(
        impl: AuthRepositoryImpl
    ): AuthRepository

    @Singleton
    @Binds
    abstract fun bindsSpotRepository(
        impl: SpotRepositoryImpl
    ): SpotRepository

    @Singleton
    @Binds
    abstract fun bindsTokenLocalRepository(
        impl: TokenRepositoryImpl
    ): TokenRepository

    @Singleton
    @Binds
    abstract fun bindsOnboardingRepository(
        impl: OnboardingRepositoryImpl
    ): OnboardingRepository

    @Singleton
    @Binds
    abstract fun bindsUploadRepository(
        impl: UploadRepositoryImpl
    ): UploadRepository

    @Singleton
    @Binds
    abstract fun bindsAreaVerificationRepository(
        impl: AreaVerificationRepositoryImpl
    ): AreaVerificationRepository

    @Singleton
    @Binds
    abstract fun bindsMapRepository(
        impl: MapRepositoryImpl
    ): MapRepository
}