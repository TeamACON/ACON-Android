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

    fun onCardClicked(id: String) = intent {
        val updatedSelectedCard = when {
            state.selectedCard.isEmpty() -> setOf(id)
            state.selectedCard.contains(id) -> state.selectedCard - id
            else -> setOf(id)
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

    fun skipConfirmed() = intent {
        reduce {
            state.copy(openCloseDialog = false)
        }
        postSideEffect(FrequentPlaceSelectScreenSideEffect.NavigateToLastPage)
    }

    fun navigateToPreviousPage() = intent {
        postSideEffect(FrequentPlaceSelectScreenSideEffect.NavigateToPreviousPage)
    }

    fun navigateToNextPage() = intent {
        onboardingRepository.postFavoriteSpotType(state.selectedCard.first().toString())
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
    data object NavigateToLastPage: FrequentPlaceSelectScreenSideEffect
}