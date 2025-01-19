package com.acon.feature.onboarding.screen.FrequentPlaceSelectScreen

import com.acon.core.utils.feature.base.BaseContainerHost
import com.acon.domain.repository.OnboardingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

private const val ONBOARDING_TOTAL_PAGES = 5;

@HiltViewModel
class FrequentPlaceSelectScreenViewModel @Inject constructor(
    private val onboardingRepository: OnboardingRepository
) : BaseContainerHost<FrequentPlaceSelectScreenState, FrequentPlaceSelectScreenSideEffect>() {

    override val container: Container<FrequentPlaceSelectScreenState, FrequentPlaceSelectScreenSideEffect> =
        container(
            initialState = FrequentPlaceSelectScreenState(  )
        )

    fun onCardClicked(text: String) = intent {
        val updatedSelectedCard = when {
            state.selectedCard.isEmpty() -> setOf(text)
            state.selectedCard.contains(text) -> state.selectedCard - text
            else -> setOf(text)
        }

        reduce {
            state.copy(selectedCard = updatedSelectedCard)
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
        postSideEffect(FrequentPlaceSelectScreenSideEffect.NavigateToPreviousPage)
    }

    fun navigateToNextPage() = intent {
        onboardingRepository.postFavoriteSpotStyle(state.selectedCard.toString())
        postSideEffect(FrequentPlaceSelectScreenSideEffect.NavigateToNextPage)
    }
}

data class FrequentPlaceSelectScreenState(
    val selectedCard: Set<String> = emptySet(),
    val totalPages: Int = ONBOARDING_TOTAL_PAGES,
    val currentPage: Int = 2,
    val openCloseDialog: Boolean = false,
)

sealed interface FrequentPlaceSelectScreenSideEffect {
    data object NavigateToPreviousPage: FrequentPlaceSelectScreenSideEffect
    data object NavigateToNextPage: FrequentPlaceSelectScreenSideEffect
}