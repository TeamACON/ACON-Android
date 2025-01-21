package com.acon.data.di

import com.acon.data.repository.AuthRepositoryImpl
import com.acon.data.repository.SpotRepositoryImpl
import com.acon.data.repository.UploadRepositoryImpl
import com.acon.domain.repository.AuthRepository
import com.acon.domain.repository.SpotRepository
import com.acon.domain.repository.UploadRepository
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
    abstract fun bindsUploadRepository(
        impl: UploadRepositoryImpl
    ): UploadRepository
}