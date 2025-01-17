package com.acon.feature.onboarding.screen.PreferredPlaceSelectScreen

import com.acon.core.utils.feature.base.BaseContainerHost
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

private const val ONBOARDING_TOTAL_PAGES = 5;

@HiltViewModel
class PreferredPlaceSelectViewModel @Inject constructor(
) : BaseContainerHost<PreferredPlaceSelectScreenState, PreferredPlaceSelectScreenSideEffect>() {

    override val container: Container<PreferredPlaceSelectScreenState, PreferredPlaceSelectScreenSideEffect> =
        container(
            initialState = PreferredPlaceSelectScreenState(  )
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
        postSideEffect(PreferredPlaceSelectScreenSideEffect.NavigateToPreviousPage)
    }

    fun navigateToNextPage() = intent {
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