package com.acon.android.data.di

import com.acon.android.data.repository.SocialRepositoryImpl
import com.acon.android.domain.repository.SocialRepository
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