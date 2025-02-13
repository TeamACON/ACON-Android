package com.acon.android.domain.error.spot

sealed class FetchRecentNavigationLocationError : com.acon.android.domain.error.RootError() {

    class SpaceNotFoundError : FetchRecentNavigationLocationError() {
        override val code: Int = 40403
    }

    companion object : com.acon.android.domain.error.ErrorFactory {
        override fun createErrorInstances(): Array<com.acon.android.domain.error.RootError> {
            return arrayOf(
                SpaceNotFoundError(),
            )
        }
    }
}