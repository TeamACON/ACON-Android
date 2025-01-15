package com.acon.data.di

import com.acon.data.api.remote.SpotApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ApiModule {

    @Singleton
    @Provides
    fun providesSpotApi(
        retrofit: Retrofit
    ): SpotApi {
        return retrofit.create(SpotApi::class.java)
    }
}