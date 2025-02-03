package com.acon.feature.onboarding.screen.PrefResultLoadingScreen

import android.util.Log
import com.acon.core.utils.feature.base.BaseContainerHost
import com.acon.domain.error.onboarding.PostOnboardingResultError
import com.acon.domain.repository.OnboardingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class PrefResultLoadingScreenViewModel @Inject constructor(
    private val onboardingRepository: OnboardingRepository
) : BaseContainerHost<PrefResultLoadingScreenState, PrefResultLoadingScreenSideEffect>() {

    override val container = container<PrefResultLoadingScreenState, PrefResultLoadingScreenSideEffect>(
        PrefResultLoadingScreenState.Loading
    ) { }

    init {
        observeOnboardingResult()
    }

    private fun observeOnboardingResult() = intent {
        onboardingRepository.onboardingResultStateFlow.collect { result ->
            when {
                result?.isSuccess == true -> {
                    delay(1000L)
                    reduce { PrefResultLoadingScreenState.LoadSucceed }
                    delay(2000L)
                    navigateToSpotListView()
                }
                result?.isFailure == true -> {
                    reduce { PrefResultLoadingScreenState.LoadFailed }

                    val throwable = result.exceptionOrNull()
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
                            "Unexpected Error: ${throwable?.localizedMessage}",
                            throwable
                        )
                    }
                }
                else -> {  }
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