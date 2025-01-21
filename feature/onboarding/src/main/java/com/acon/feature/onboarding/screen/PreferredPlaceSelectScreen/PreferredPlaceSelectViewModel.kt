package com.acon.feature.onboarding.screen.PreferredPlaceSelectScreen

import com.acon.core.utils.feature.base.BaseContainerHost
import com.acon.domain.repository.OnboardingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

private const val ONBOARDING_TOTAL_PAGES = 5;

@HiltViewModel
class PreferredPlaceSelectViewModel @Inject constructor(
    private val onboardingRepository: OnboardingRepository
) : BaseContainerHost<PreferredPlaceSelectScreenState, PreferredPlaceSelectScreenSideEffect>() {

    override val container: Container<PreferredPlaceSelectScreenState, PreferredPlaceSelectScreenSideEffect> =
        container(
            initialState = PreferredPlaceSelectScreenState(  )
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

    fun navigateToPreviousPage() = intent {
        postSideEffect(PreferredPlaceSelectScreenSideEffect.NavigateToPreviousPage)
    }

    fun navigateToNextPage() = intent {
        onboardingRepository.postFavoriteSpotStyle(state.selectedCard.toString())
        postSideEffect(PreferredPlaceSelectScreenSideEffect.NavigateToNextPage)
    }
}

data class PreferredPlaceSelectScreenState(
    val selectedCard: Set<String> = emptySet(),
    val totalPages: Int = ONBOARDING_TOTAL_PAGES,
    val currentPage: Int = 3,
    val openCloseDialog: Boolean = false,
)

sealed interface PreferredPlaceSelectScreenSideEffect {
    data object NavigateToPreviousPage: PreferredPlaceSelectScreenSideEffect
    data object NavigateToNextPage: PreferredPlaceSelectScreenSideEffect
}