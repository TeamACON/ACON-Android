package com.acon.data.di

import com.acon.data.repository.AuthRepositoryImpl
import com.acon.data.repository.SpotRepositoryImpl
import com.acon.data.repository.TokenLocalRepositoryImpl
import com.acon.domain.repository.AuthRepository
import com.acon.domain.repository.SpotRepository
import com.acon.domain.repository.TokenLocalRepository
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
        impl: TokenLocalRepositoryImpl
    ): TokenLocalRepository
}