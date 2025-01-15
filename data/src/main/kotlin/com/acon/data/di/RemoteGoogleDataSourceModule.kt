package com.acon.data.di

import android.content.Context
import androidx.credentials.CredentialManager
import com.acon.data.BuildConfig
import com.acon.data.datasource.remote.TokenRemoteDataSource
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object RemoteGoogleDataSourceModule {
    @Provides
    @ActivityScoped
    fun provideGoogleIdOptions(): GetGoogleIdOption {
        return GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setAutoSelectEnabled(false)
            .setServerClientId(BuildConfig.GOOGLE_CLIENT_ID)
            .build()
    }

    @Provides
    @ActivityScoped
    fun provideCredentialManager(@ActivityContext context: Context): CredentialManager {
        return CredentialManager.create(context)
    }

    @Provides
    @ActivityScoped
    fun provideTokenRemoteDataSource(
        credentialManager: CredentialManager,
        googleIdOption: GetGoogleIdOption,
        @ActivityContext context: Context
    ): TokenRemoteDataSource {
        return TokenRemoteDataSource(credentialManager, googleIdOption, context)
    }
}