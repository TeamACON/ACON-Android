package com.acon.android.di

import com.acon.android.core.common.DefaultDispatcher
import com.acon.android.core.common.IoDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoroutineScopesModule {

    @Singleton
    @Provides
    @IoDispatcher
    fun providesIoCoroutineScope(
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ) = CoroutineScope(SupervisorJob() + ioDispatcher)

    @Singleton
    @Provides
    @DefaultDispatcher
    fun providesDefaultCoroutineScope(
        @DefaultDispatcher defaultDispatcher: CoroutineDispatcher
    ) = CoroutineScope(SupervisorJob() + defaultDispatcher)
}