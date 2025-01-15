package com.acon.data.datasource.remote

import android.content.Context
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class TokenRemoteDataSource @Inject constructor(
    private val credentialManager: CredentialManager,
    private val googleIdOption: GetGoogleIdOption,
    @ActivityContext private val context: Context
) {

    suspend fun signIn(): Result<Unit> {
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
                            Result.success(credential)
                        } catch (e: GoogleIdTokenParsingException) {
                            throw e
                        }
                    } else {
                        throw IllegalStateException("Unsupported credential type")
                    }
                }
                else -> {
                    throw IllegalStateException("Unknown credential type")
                }
            }
        }
    }
}
