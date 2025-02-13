package com.acon.android.domain.error.spot

import com.acon.android.domain.error.ErrorFactory
import com.acon.android.domain.error.RootError

sealed class GetSpotMenuListError : RootError() {
    class SpaceNotFoundError : GetSpotMenuListError() {
        override val code: Int = 40403
    }

    companion object : ErrorFactory {
        override fun createErrorInstances(): Array<RootError> {
            return arrayOf(
                SpaceNotFoundError()
            )
        }
    }
}