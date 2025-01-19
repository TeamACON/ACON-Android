package com.acon.data.di

import com.acon.data.repository.GoogleTokenRepositoryImpl
import com.acon.domain.repository.GoogleTokenRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
abstract class GoogleTokenRepositoryModule {
    @Binds
    @ActivityScoped
    abstract fun bindGoogleSignInRepository(
        googleTokenRepositoryImpl: GoogleTokenRepositoryImpl
    ): GoogleTokenRepository
}