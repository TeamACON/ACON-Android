package com.acon.android.data.datasource.remote

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.acon.android.data.error.ErrorMessages
import com.acon.android.data.BuildConfig
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

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

    suspend fun signIn(): Result<String> = runCatching {
        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        credentialManager.getCredential(
            request = request,
            context = context
        )
    }.fold(
        onSuccess = { response ->
            val credential = response.credential
            if (credential is CustomCredential && credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                val idToken = GoogleIdTokenCredential.createFrom(credential.data).idToken
                Result.success(idToken)
            } else {
                throw IllegalStateException(ErrorMessages.UNKNOWN_CREDENTIAL_TYPE)
            }
        },
        onFailure = { e ->
            Result.failure(
                when (e) {
                    is CancellationException -> CancellationException(ErrorMessages.USER_CANCELED)
                    is GoogleIdTokenParsingException -> e
                    is NoSuchElementException -> e
                    is SecurityException -> e
                    else -> IllegalStateException(ErrorMessages.UNKNOWN_ERROR, e)
                }
            )
        }
    )
}