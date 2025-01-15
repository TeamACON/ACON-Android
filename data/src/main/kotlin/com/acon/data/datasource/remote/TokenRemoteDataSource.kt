package com.acon.data.datasource.remote

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.acon.data.BuildConfig
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class TokenRemoteDataSource @Inject constructor(
    @ActivityContext private val context: Context
) {

    private val googleIdOption: GetGoogleIdOption by lazy {
        GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setAutoSelectEnabled(false)
            .setServerClientId(BuildConfig.GOOGLE_CLIENT_ID)
            .build()
    }

    private val credentialManager: CredentialManager by lazy {
        CredentialManager.create(context)
    }

    suspend fun signIn(): Result<String> {
        return runCatching {
            val request = GetCredentialRequest.Builder()
                .addCredentialOption(googleIdOption)
                .build()

            val response = credentialManager.getCredential(
                request = request,
                context = context
            )

            when (val credential = response.credential) {
                is CustomCredential -> {
                    if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                        try {
                            val googleIdCredential = GoogleIdTokenCredential
                                .createFrom(credential.data)
                            googleIdCredential.idToken
                        } catch (e: GoogleIdTokenParsingException) {
                            throw e
                        }
                    } else {
                        throw IllegalStateException("Unsupported credential type")
                    }
                }
                else -> {
                    throw IllegalStateException("Unsupported or unknown credential type")
                }
            }
        }
    }
}
