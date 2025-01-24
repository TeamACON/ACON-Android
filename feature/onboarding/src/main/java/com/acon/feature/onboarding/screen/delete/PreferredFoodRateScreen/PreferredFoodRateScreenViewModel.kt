package com.acon.feature.onboarding.screen.delete.PreferredFoodRateScreen

import com.acon.core.utils.feature.base.BaseContainerHost
import com.acon.domain.repository.OnboardingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

private const val ONBOARDING_TOTAL_PAGES = 5;

@HiltViewModel
class PreferredFoodRateScreenViewModel @Inject constructor(
    private val onboardingRepository: OnboardingRepository
) : BaseContainerHost<RatePreferFoodScreenState, RatePreferFoodScreenSideEffect>() {

    override val container: Container<RatePreferFoodScreenState, RatePreferFoodScreenSideEffect> =
        container(
            initialState = RatePreferFoodScreenState(  )
        )

    fun onCardClicked(id: String) = intent {
        val updatedSelectedCard = state.selectedCard.toMutableList()

        if (updatedSelectedCard.contains(id)) {
            updatedSelectedCard.remove(id)
        } else if (updatedSelectedCard.size < 3) {
            updatedSelectedCard.add(id)
        }
        reduce {
            state.copy(selectedCard = updatedSelectedCard.toSet()) // Set으로 변환
        }
    }

    fun showDialog() = intent {
        reduce {
            state.copy(openCloseDialog = true)
        }
    }

    fun hideDialog() = intent {
        reduce {
            state.copy(openCloseDialog = false)
        }
    }

    fun skipConfirmed() = intent {
        reduce {
            state.copy(openCloseDialog = false)
        }
        postSideEffect(RatePreferFoodScreenSideEffect.NavigateToLastPage)
    }

    fun navigateToPreviousPage() = intent {
        postSideEffect(RatePreferFoodScreenSideEffect.NavigateToPreviousPage)
    }

    fun navigateToNextPage() = intent {
        onboardingRepository.postFavoriteCuisineRank(state.selectedCard.toList())
        postSideEffect(RatePreferFoodScreenSideEffect.NavigateToNextPage)
    }
}

data class RatePreferFoodScreenState(
    val selectedCard: Set<String> = emptySet(),
    val totalPages: Int = ONBOARDING_TOTAL_PAGES,
    val currentPage: Int = 1,
    val openCloseDialog: Boolean = false,
)

sealed interface RatePreferFoodScreenSideEffect {
    data object NavigateToPreviousPage: RatePreferFoodScreenSideEffect
    data object NavigateToNextPage: RatePreferFoodScreenSideEffect
    data object NavigateToLastPage: RatePreferFoodScreenSideEffect
}