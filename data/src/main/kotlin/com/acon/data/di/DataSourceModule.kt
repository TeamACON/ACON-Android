package com.acon.data.di

import com.acon.data.datasource.local.OnboardingLocalDataSource
import com.acon.data.repository.OnboardingLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindsOnboardingLocalDataSource(onboardingLocalDataSourceImpl: OnboardingLocalDataSourceImpl) : OnboardingLocalDataSource
}