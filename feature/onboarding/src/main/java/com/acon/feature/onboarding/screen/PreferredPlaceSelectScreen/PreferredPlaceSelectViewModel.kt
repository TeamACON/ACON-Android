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

//        val updatedSelectedCard = state.selectedCard.toMutableList()
//
//        if (updatedSelectedCard.isEmpty()) updatedSelectedCard.add(text) //비어있던 경우
//        else if (updatedSelectedCard.contains(text)) updatedSelectedCard.remove(text) //안 비었는데 & 원래 있던 경우
//        else { // 안 비었는데 & 원래 없던 경우
//            updatedSelectedCard.clear() // 원래 선택돼 있던 것 비우고
//            updatedSelectedCard.add(text) // 누른 것 추가
//        }

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