package com.acon.feature.onboarding.screen.FrequentPlaceSelectScreen

import com.acon.core.utils.feature.base.BaseContainerHost
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

private const val ONBOARDING_TOTAL_PAGES = 5;

@HiltViewModel
class FrequentPlaceSelectScreenViewModel @Inject constructor(
) : BaseContainerHost<FrequentPlaceSelectScreenState, FrequentPlaceSelectScreenSideEffect>() {

    override val container: Container<FrequentPlaceSelectScreenState, FrequentPlaceSelectScreenSideEffect> =
        container(
            initialState = FrequentPlaceSelectScreenState(  )
        )

    fun onCardClicked(text: String) = intent {
        val updatedSelectedCard = state.selectedCard.toMutableList()

        if (updatedSelectedCard.isEmpty()) updatedSelectedCard.add(text)
        else if (updatedSelectedCard.contains(text)) updatedSelectedCard.remove(text)

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
        postSideEffect(FrequentPlaceSelectScreenSideEffect.NavigateToPreviousPage)
    }

    fun navigateToNextPage() = intent {
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