package com.acon.feature.onboarding.screen.PrefResultLoadingScreen

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.acon.core.utils.feature.base.BaseContainerHost
import com.acon.domain.error.onboarding.PostOnboardingResultError
import com.acon.domain.repository.OnboardingRepository
import com.acon.feature.onboarding.OnboardingRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class PrefResultLoadingScreenViewModel @Inject constructor(
    private val onboardingRepository: OnboardingRepository,
    savedStateHandle: SavedStateHandle
) : BaseContainerHost<PrefResultLoadingScreenState, PrefResultLoadingScreenSideEffect>() {

    override val container = container<PrefResultLoadingScreenState, PrefResultLoadingScreenSideEffect>(
        PrefResultLoadingScreenState.Loading
    ) {

    }

    val arguments = savedStateHandle.toRoute<OnboardingRoute.LastLoading>()

    fun postOnboardingResult() = intent {

        onboardingRepository.postOnboardingResult(
            dislikeFoodList = arguments.onboardingResult.dislikeFoodList,
            favoriteCuisineRank = arguments.onboardingResult.favoriteCuisineRank,
            favoriteSpotType = arguments.onboardingResult.favoriteSpotType,
            favoriteSpotStyle = arguments.onboardingResult.favoriteSpotStyle,
            favoriteSpotRank = arguments.onboardingResult.favoriteSpotRank
        ).onSuccess {
            delay(1000L)
            reduce {
                PrefResultLoadingScreenState.LoadSucceed
            }
            delay(2000L)
            navigateToSpotListView()
        }.onFailure { throwable ->
            when (throwable) {
                is PostOnboardingResultError.InvalidDislikeFood -> Log.e(
                    "OnboardingError",
                    "Invalid Dislike Food: ${throwable.code}"
                )
                is PostOnboardingResultError.InvalidCuisine -> Log.e(
                    "OnboardingError",
                    "Invalid Cuisine: ${throwable.code}"
                )
                is PostOnboardingResultError.InvalidSpotType -> Log.e(
                    "OnboardingError",
                    "Invalid Spot Type: ${throwable.code}"
                )
                is PostOnboardingResultError.InvalidSpotStyle -> Log.e(
                    "OnboardingError",
                    "Invalid Spot Style: ${throwable.code}"
                )
                is PostOnboardingResultError.InvalidSpotRank -> Log.e(
                    "OnboardingError",
                    "Invalid Spot Rank: ${throwable.code}"
                )
                is PostOnboardingResultError.InvalidFavSpotRankSize -> Log.e(
                    "OnboardingError",
                    "Invalid Favorite Spot Rank Size: ${throwable.code}"
                )
                is PostOnboardingResultError.InvalidFavCuisineRankSize -> Log.e(
                    "OnboardingError",
                    "Invalid Favorite Cuisine Rank Size: ${throwable.code}"
                )
                else -> Log.e(
                    "OnboardingError",
                    "Unexpected Error: ${throwable.localizedMessage}",
                    throwable
                )
            }
        }
    }

    fun navigateToSpotListView() = intent {
        postSideEffect(PrefResultLoadingScreenSideEffect.navigateToSpotListView)
    }

}

sealed interface PrefResultLoadingScreenState {
    data object Loading : PrefResultLoadingScreenState
    data object LoadSucceed : PrefResultLoadingScreenState
    data object LoadFailed: PrefResultLoadingScreenState
}


sealed interface PrefResultLoadingScreenSideEffect {
    data object navigateToSpotListView: PrefResultLoadingScreenSideEffect
}