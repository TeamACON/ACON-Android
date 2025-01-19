package com.acon.feature.onboarding.screen.PrefResultLoadingScreen

import com.acon.core.utils.feature.base.BaseContainerHost
import com.acon.domain.repository.OnboardingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class PrefResultLoadingScreenViewModel @Inject constructor(
    private val onboardingRepository: OnboardingRepository,
) : BaseContainerHost<PrefResultLoadingScreenState, PrefResultLoadingScreenSideEffect>() {

    override val container = container<PrefResultLoadingScreenState, PrefResultLoadingScreenSideEffect>(PrefResultLoadingScreenState.Loading) {

    }

    fun postOnboardingResult() = intent {

        val onboardingResult = onboardingRepository.getOnboardingResults()

        onboardingRepository.postOnboardingResult(
            dislikeFoodList = onboardingResult.dislikeFoodList,
            favoriteCuisineRank = onboardingResult.favoriteCuisineRank,
            favoriteSpotType = onboardingResult.favoriteSpotType,
            favoriteSpotStyle = onboardingResult.favoriteSpotStyle,
            favoriteSpotRank = onboardingResult.favoriteSpotRank
        ).onSuccess {
            reduce {
                PrefResultLoadingScreenState.LoadSucceed
            }
            delay(2000L)
            navigateToSpotListView()
        }.onFailure {
            //실패하면 어케함?
        }
    }

    fun navigateToSpotListView() = intent {
        postSideEffect(PrefResultLoadingScreenSideEffect.navigateToSpotListView)
    }
}

sealed interface PrefResultLoadingScreenState {
    data object Loading : PrefResultLoadingScreenState
    data object LoadSucceed : PrefResultLoadingScreenState
}


sealed interface PrefResultLoadingScreenSideEffect {
    data object navigateToSpotListView: PrefResultLoadingScreenSideEffect
}