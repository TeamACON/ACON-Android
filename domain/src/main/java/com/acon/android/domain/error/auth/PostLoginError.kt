package com.acon.android.domain.error.auth

import com.acon.android.domain.error.RootError

sealed class PostLoginError : RootError() {
    class InvalidSocialType : PostLoginError() {
        override val code: Int = 40009
    }
    class InvalidIdTokenSignature : PostLoginError() {
        override val code: Int = 40010
    }
    class GooglePublicKeyDownloadFailed : PostLoginError() {
        override val code: Int = 50002
    }

    companion object : com.acon.android.domain.error.ErrorFactory {
        override fun createErrorInstances(): Array<RootError> {
            return arrayOf(
                InvalidSocialType(),
                InvalidIdTokenSignature(),
                GooglePublicKeyDownloadFailed(),
            )
        }
    }
}