package com.acon.data.di

import com.acon.data.repository.SocialRepositoryImpl
import com.acon.domain.repository.SocialRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
abstract class SocialRepositoryModule {
    @Binds
    @ActivityScoped
    abstract fun bindGoogleSignInRepository(
        impl: SocialRepositoryImpl
    ): SocialRepository
}