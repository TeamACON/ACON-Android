package com.acon.domain.error.spot

import com.acon.domain.error.ErrorFactory
import com.acon.domain.error.RootError

sealed class GetSpotMenuListError : RootError() {
    class SpaceNotFoundError : GetSpotMenuListError() {
        override val code: Int = 40402
    }

    companion object : ErrorFactory {
        override fun createErrorInstances(): Array<RootError> {
            return arrayOf(
                SpaceNotFoundError()
            )
        }
    }
}