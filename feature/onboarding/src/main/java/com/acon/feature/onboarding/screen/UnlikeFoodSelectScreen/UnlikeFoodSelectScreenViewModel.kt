package com.acon.feature.onboarding.screen.UnlikeFoodSelectScreen

import com.acon.core.utils.feature.base.BaseContainerHost
import com.acon.domain.repository.OnboardingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.selects.select
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

private const val ONBOARDING_TOTAL_PAGES = 5;

@HiltViewModel
class UnlikeFoodScreenViewModel @Inject constructor(
    private val onboardingRepository: OnboardingRepository
) : BaseContainerHost<UnlikeFoodScreenState, UnlikeFoodScreenSideEffect>() {

    override val container: Container<UnlikeFoodScreenState, UnlikeFoodScreenSideEffect> =
        container(
            initialState = UnlikeFoodScreenState(  )
        )

    fun onCardClicked(id: String) = intent {

        if (id == "") { //없음인 경우
            if(state.selectedCard.contains(id)){
                reduce {
                    state.copy(
                        selectedCard = emptySet()
                    )
                }
            } else {
                reduce {
                    state.copy(
                        selectedCard = setOf(id)
                    )
                }
            }
            reduce {
                state.copy(
                    isNothingClicked = !state.isNothingClicked,
                )
            }

        } else {
            val updatedSelectedCard = if (state.selectedCard.contains(id)) {
                state.selectedCard - id
            } else {
                state.selectedCard + id
            }

            reduce {
                state.copy(
                    selectedCard = updatedSelectedCard,
                    isNothingClicked = false
                )
            }
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
        postSideEffect(UnlikeFoodScreenSideEffect.NavigateToLastPage)
    }

    fun navigateToNextPage() = intent {

        val disLikeFoodList = if (state.selectedCard.contains("")) emptySet()
                                else state.selectedCard
        onboardingRepository.postDislikeFood(disLikeFoodList)
        postSideEffect(UnlikeFoodScreenSideEffect.NavigateToNextPage)
    }
}

data class UnlikeFoodScreenState(
    val isNothingClicked: Boolean = false,
    val selectedCard: Set<String> = emptySet(),
    val totalPages: Int = ONBOARDING_TOTAL_PAGES,
    val currentPage: Int = 0,
    val openCloseDialog: Boolean = false,
)

sealed interface UnlikeFoodScreenSideEffect {
    data object NavigateToNextPage: UnlikeFoodScreenSideEffect
    data object NavigateToLastPage: UnlikeFoodScreenSideEffect
}