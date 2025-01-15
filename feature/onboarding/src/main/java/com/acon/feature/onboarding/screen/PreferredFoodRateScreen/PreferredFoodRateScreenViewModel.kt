package com.acon.feature.onboarding.screen.PreferredFoodRateScreen

import com.acon.base.BaseContainerHost
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

const val ONBOARDING_TOTAL_PAGES = 5;

@HiltViewModel
class PreferredFoodRateScreenViewModel @Inject constructor(
) : BaseContainerHost<RatePreferFoodScreenState, RatePreferFoodScreenSideEffect>() {

    override val container: Container<RatePreferFoodScreenState, RatePreferFoodScreenSideEffect> =
        container(
            initialState = RatePreferFoodScreenState(  )
        )

    fun onCardClicked(text: String) = intent {

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
        postSideEffect(RatePreferFoodScreenSideEffect.NavigateToPreviousPage)
    }

    fun navigateToNextPage() = intent {
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
}