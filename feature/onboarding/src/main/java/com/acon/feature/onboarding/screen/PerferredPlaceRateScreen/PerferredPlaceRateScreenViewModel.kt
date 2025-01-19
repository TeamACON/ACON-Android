package com.acon.feature.onboarding.screen.PerferredPlaceRateScreen

import com.acon.core.utils.feature.base.BaseContainerHost
import com.acon.domain.repository.OnboardingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

private const val ONBOARDING_TOTAL_PAGES = 5;

@HiltViewModel
class PreferredPlaceRateScreenViewModel @Inject constructor(
    private val onboardingRepository: OnboardingRepository
) : BaseContainerHost<PreferredPlaceRateScreenState, PreferredPlaceRateScreenSideEffect>() {

    override val container: Container<PreferredPlaceRateScreenState, PreferredPlaceRateScreenSideEffect> =
        container(
            initialState = PreferredPlaceRateScreenState(  )
        )

    fun onCardClicked(text: String) = intent {

        val updatedSelectedCard = state.selectedCard.toMutableList()

        if (updatedSelectedCard.contains(text)) {
            updatedSelectedCard.remove(text)
        } else if (updatedSelectedCard.size < 4) {
            updatedSelectedCard.add(text)
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

    fun navigateToPreviousPage() = intent {
        postSideEffect(PreferredPlaceRateScreenSideEffect.NavigateToPreviousPage)
    }

    fun navigateToNextPage() = intent {
        onboardingRepository.postFavoriteCuisineRank(state.selectedCard.toList())

        //만약 local DB에 정보가 다 들어가 있으면.
        postSideEffect(PreferredPlaceRateScreenSideEffect.NavigateToNextPage)
    }
}

data class PreferredPlaceRateScreenState(
    val selectedCard: Set<String> = emptySet(),
    val totalPages: Int = ONBOARDING_TOTAL_PAGES,
    val currentPage: Int = 4,
    val openCloseDialog: Boolean = false,
)

sealed interface PreferredPlaceRateScreenSideEffect {
    data object NavigateToPreviousPage: PreferredPlaceRateScreenSideEffect
    data object NavigateToNextPage: PreferredPlaceRateScreenSideEffect
}