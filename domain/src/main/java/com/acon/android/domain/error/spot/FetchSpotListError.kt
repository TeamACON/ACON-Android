package com.acon.android.domain.error.spot

sealed class FetchSpotListError : com.acon.android.domain.error.RootError() {

    class InvalidSpotType : FetchSpotListError() {
        override val code: Int = 40018
    }
    class InvalidCategory : FetchSpotListError() {
        override val code: Int = 40019
    }
    class InvalidOption : FetchSpotListError() {
        override val code: Int = 40020
    }
    class NonMatchingSpotTypeAndCategory : FetchSpotListError() {
        override val code: Int = 40021
    }
    class NonMatchingCategoryAndOption : FetchSpotListError() {
        override val code: Int = 40022
    }

    companion object : com.acon.android.domain.error.ErrorFactory {
        override fun createErrorInstances(): Array<com.acon.android.domain.error.RootError> {
            return arrayOf(
                InvalidSpotType(),
                InvalidCategory(),
                InvalidOption(),
                NonMatchingSpotTypeAndCategory(),
                NonMatchingCategoryAndOption()
            )
        }
    }
}