package com.acon.feature.onboarding.screen.UnlikeFoodSelectScreen

import com.acon.base.BaseContainerHost
import com.acon.feature.onboarding.R
import com.acon.feature.onboarding.component.FoodItem
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

const val ONBOARDING_TOTAL_PAGES = 6;

@HiltViewModel
class UnlikeFoodScreenViewModel @Inject constructor(
) : BaseContainerHost<UnlikeFoodScreenState, UnlikeFoodScreenSideEffect>() {

    override val container: Container<UnlikeFoodScreenState, UnlikeFoodScreenSideEffect> =
        container(
            initialState = UnlikeFoodScreenState(
                foodItems = listOf(
                    FoodItem(R.drawable.food_img_1, "닭발", false),
                    FoodItem(R.drawable.food_img_2, "회/육회", false),
                    FoodItem(R.drawable.food_img_3, "곱창/대창/막창", false),
                    FoodItem(R.drawable.food_img_4, "순대/선지", false),
                    FoodItem(R.drawable.food_img_5, "양고기", false),
                    FoodItem(0, "없음", false)
                )
            )
        )

    fun onCardClicked(text: String) = intent {

        //없음 누른 경우
        if (text == "없음") {
            reduce {
                state.copy(
                    isNothingClicked = !state.isNothingClicked,
                    selectedCard = if (!state.isNothingClicked) setOf(text) else state.selectedCard
                )
            }
        } else { //없음이 아닌 다른 FoodItem 누른 경우

            val updatedSelectedCard = if (state.selectedCard.contains(text)) {
                state.selectedCard - text
            } else {
                state.selectedCard + text
            }

            reduce {
                state.copy(
                    selectedCard = updatedSelectedCard,
                    isNothingClicked = false
                )
            }
        }
    }

    fun onSkipClicked() = intent {
        postSideEffect(UnlikeFoodScreenSideEffect.ShowCloseDialog)
    }

    fun onBackClicked() = intent {
        postSideEffect(UnlikeFoodScreenSideEffect.NavigateToPreviousPage)
    }

    fun navigateToNextPage() = intent {
        postSideEffect(UnlikeFoodScreenSideEffect.NavigateToNextPage)
    }


}

data class UnlikeFoodScreenState(
    val foodItems: List<FoodItem> = emptyList(),
    val isNothingClicked: Boolean = false,
    val selectedCard: Set<String> = emptySet(),
    val totalPages: Int = ONBOARDING_TOTAL_PAGES,
    val currentPage: Int = 0,
)

sealed interface UnlikeFoodScreenSideEffect {
    data object ShowCloseDialog: UnlikeFoodScreenSideEffect
    data object NavigateToPreviousPage: UnlikeFoodScreenSideEffect
    data object NavigateToNextPage: UnlikeFoodScreenSideEffect
}