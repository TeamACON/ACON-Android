package com.acon.android.domain.error.onboarding

import com.acon.android.domain.error.RootError
import com.acon.android.domain.error.ErrorFactory

sealed class PostOnboardingResultError : com.acon.android.domain.error.RootError() {

    class InvalidDislikeFood : com.acon.android.domain.error.onboarding.PostOnboardingResultError() {
        override val code: Int = 40013
    }
    class InvalidCuisine : com.acon.android.domain.error.onboarding.PostOnboardingResultError() {
        override val code: Int = 40014
    }
    class InvalidSpotType : com.acon.android.domain.error.onboarding.PostOnboardingResultError() {
        override val code: Int = 40015
    }
    class InvalidSpotStyle : com.acon.android.domain.error.onboarding.PostOnboardingResultError() {
        override val code: Int = 40016
    }
    class InvalidSpotRank : com.acon.android.domain.error.onboarding.PostOnboardingResultError() {
        override val code: Int = 40017
    }
    class InvalidFavSpotRankSize : com.acon.android.domain.error.onboarding.PostOnboardingResultError() {
        override val code: Int = 40030
    }
    class InvalidFavCuisineRankSize : com.acon.android.domain.error.onboarding.PostOnboardingResultError() {
        override val code: Int = 40031
    }

    companion object : com.acon.android.domain.error.ErrorFactory {
        override fun createErrorInstances(): Array<com.acon.android.domain.error.RootError> {
            return arrayOf(
                com.acon.android.domain.error.onboarding.PostOnboardingResultError.InvalidDislikeFood(),
                com.acon.android.domain.error.onboarding.PostOnboardingResultError.InvalidCuisine(),
                com.acon.android.domain.error.onboarding.PostOnboardingResultError.InvalidSpotType(),
                com.acon.android.domain.error.onboarding.PostOnboardingResultError.InvalidSpotStyle(),
                com.acon.android.domain.error.onboarding.PostOnboardingResultError.InvalidSpotRank(),
                com.acon.android.domain.error.onboarding.PostOnboardingResultError.InvalidFavSpotRankSize(),
                com.acon.android.domain.error.onboarding.PostOnboardingResultError.InvalidFavCuisineRankSize()
            )
        }
    }
}