package com.acon.data.di

import com.acon.data.repository.SpotRepositoryImpl
import com.acon.domain.repository.SpotRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindsSpotRepository(
        impl: SpotRepositoryImpl
    ): SpotRepository
}