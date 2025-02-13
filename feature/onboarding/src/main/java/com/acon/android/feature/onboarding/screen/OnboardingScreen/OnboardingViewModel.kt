package com.acon.android.feature.onboarding.screen.OnboardingScreen

import android.os.Parcelable
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.acon.android.core.utils.feature.base.BaseContainerHost
import com.acon.android.domain.repository.OnboardingRepository
import com.acon.android.feature.onboarding.type.FoodItems
import com.acon.android.feature.onboarding.type.FoodTypeItems
import com.acon.android.feature.onboarding.type.MoodItems
import com.acon.android.feature.onboarding.type.PlaceItems
import com.acon.android.feature.onboarding.type.PreferPlaceItems
import com.acon.android.feature.onboarding.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

private const val ONBOARDING_TOTAL_PAGES = 5;

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val onboardingRepository: OnboardingRepository
) : BaseContainerHost<OnboardingScreenState, OnboardingScreenSideEffect>() {

    override val container: Container<OnboardingScreenState, OnboardingScreenSideEffect> =
        container(
            initialState = OnboardingScreenState()
        )

    fun showDialog() = intent {
        reduce {
            state.copy(showDialog = true)
        }
    }

    fun hideDialog() = intent {
        reduce {
            state.copy(showDialog = false)
        }
    }

    fun skipConfirmed() = intent {
        reduce {
            state.copy(showDialog = false)
        }
        postSideEffect(OnboardingScreenSideEffect.NavigateToSpotListView)
    }

    fun onCardClicked(id: String) = intent {
        val newPageState = when (val currentState = state.currentState) {
            is OnboardingPageState.Page1State -> onPage1CardClick(currentState, id)
            is OnboardingPageState.Page2State -> onPage2CardClick(currentState, id)
            is OnboardingPageState.Page3State -> onPage3CardClick(currentState, id)
            is OnboardingPageState.Page4State -> onPage4CardClick(currentState, id)
            is OnboardingPageState.Page5State -> onPage5CardClick(currentState, id)
        }

        reduce {
            state.copy(currentState = newPageState)
        }
    }

    private fun onPage1CardClick(
        currentState: OnboardingPageState.Page1State,
        id: String
    ): OnboardingPageState.Page1State {
        return if (id == "") { // '없음' 선택
            if (currentState.selectedCard == setOf("")) {
                currentState.copy(
                    selectedCard = emptySet(),
                    isNothingClicked = !currentState.isNothingClicked
                )
            } else {
                currentState.copy(
                    selectedCard = setOf(id),
                    isNothingClicked = !currentState.isNothingClicked
                )
            }
        } else {
            val updatedSelectedCard = if (currentState.selectedCard == setOf("")) {
                setOf(id)
            } else if (currentState.selectedCard.contains(id)){
                currentState.selectedCard - id
            } else {
                currentState.selectedCard + id
            }
            currentState.copy(
                selectedCard = updatedSelectedCard,
                isNothingClicked = false
            )
        }
    }

    private fun onPage2CardClick(currentState: OnboardingPageState.Page2State, id: String)
    : OnboardingPageState.Page2State {
        val updatedSelectedCard = currentState.selectedCard.toMutableList()

        if (updatedSelectedCard.contains(id)) {
            updatedSelectedCard.remove(id)
        } else if (updatedSelectedCard.size < 3) {
            updatedSelectedCard.add(id)
        }
        return currentState.copy(selectedCard = updatedSelectedCard.toSet()) // Set으로 변환
    }

    private fun onPage3CardClick(currentState: OnboardingPageState.Page3State, id: String)
    : OnboardingPageState.Page3State {
        val updatedSelectedCard = when {
            currentState.selectedCard.isEmpty() -> setOf(id)
            currentState.selectedCard.contains(id) -> currentState.selectedCard - id
            else -> setOf(id)
        }
        return currentState.copy(selectedCard = updatedSelectedCard)
    }

    private fun onPage4CardClick(currentState: OnboardingPageState.Page4State, id: String)
    : OnboardingPageState.Page4State {
        val updatedSelectedCard = when {
            currentState.selectedCard.isEmpty() -> setOf(id)
            currentState.selectedCard.contains(id) -> currentState.selectedCard - id
            else -> setOf(id)
        }
        return currentState.copy(selectedCard = updatedSelectedCard)
    }

    private fun onPage5CardClick(currentState: OnboardingPageState.Page5State, id: String)
    : OnboardingPageState.Page5State {

        val updatedSelectedCard = currentState.selectedCard.toMutableList()

        if (updatedSelectedCard.contains(id)) {
            updatedSelectedCard.remove(id)
        } else if (updatedSelectedCard.size < 4) {
            updatedSelectedCard.add(id)
        }
        return currentState.copy(selectedCard = updatedSelectedCard.toSet()) // Set으로 변환

    }

    fun navigateToNextPage() = intent {
        val nextPageState = when (val currentPageState = state.currentState) {
            is OnboardingPageState.Page1State -> {
                Log.e("지금 넣으려고 시도: " , "${currentPageState.selectedCard}")
                state.copy(
                    currentPage = 2,
                    onboardingResult = state.onboardingResult.copy(
                        dislikeFoodList = currentPageState.selectedCard
                    ),
                    currentState = OnboardingPageState.Page2State()
                )
            }
            is OnboardingPageState.Page2State -> {
                state.copy(
                    currentPage = 3,
                    onboardingResult = state.onboardingResult.copy(
                        favoriteCuisineRank = currentPageState.selectedCard.toList()
                    ),
                    currentState = OnboardingPageState.Page3State()
                )
            }
            is OnboardingPageState.Page3State -> {
                state.copy(
                    currentPage = 4,
                    onboardingResult = state.onboardingResult.copy(
                        favoriteSpotType = currentPageState.selectedCard.first()
                    ),
                    currentState = OnboardingPageState.Page4State()
                )
            }
            is OnboardingPageState.Page4State -> {
                state.copy(
                    currentPage = 5,
                    onboardingResult = state.onboardingResult.copy(
                        favoriteSpotStyle = currentPageState.selectedCard.first()
                    ),
                    currentState = OnboardingPageState.Page5State()
                )
            }
            is OnboardingPageState.Page5State -> {
                var updatedState = state.copy(
                    onboardingResult = state.onboardingResult.copy(
                        favoriteSpotRank = currentPageState.selectedCard.toList()
                    ),
                )
                reduce { updatedState }

                if (updatedState.onboardingResult.dislikeFoodList == setOf("")){
                    updatedState = state.copy(
                        onboardingResult = state.onboardingResult.copy(
                            dislikeFoodList = emptySet()
                        )
                    )
                }
                reduce { updatedState }

                viewModelScope.launch {
                    onboardingRepository.postOnboardingResult(
                        dislikeFoodList = updatedState.onboardingResult.dislikeFoodList,
                        favoriteCuisineRank = updatedState.onboardingResult.favoriteCuisineRank,
                        favoriteSpotType = updatedState.onboardingResult.favoriteSpotType,
                        favoriteSpotStyle = updatedState.onboardingResult.favoriteSpotStyle,
                        favoriteSpotRank = updatedState.onboardingResult.favoriteSpotRank
                    )
                }

                postSideEffect(OnboardingScreenSideEffect.NavigateToLoadingPage)
                updatedState
            }
        }

        reduce { nextPageState ?: state }
    }

    fun onBackClicked() = intent {
        val nextPageState = when (state.currentState) {
            is OnboardingPageState.Page1State -> {
                state.copy(
                )
            }
            is OnboardingPageState.Page2State -> {
                state.copy(
                    currentPage = 1,
                    currentState = OnboardingPageState.Page1State()
                )
            }
            is OnboardingPageState.Page3State -> {
                state.copy(
                    currentPage = 2,
                    currentState = OnboardingPageState.Page2State()
                )
            }
            is OnboardingPageState.Page4State -> {
                state.copy(
                    currentPage = 3,
                    currentState = OnboardingPageState.Page3State()
                )
            }
            is OnboardingPageState.Page5State -> {
                state.copy(
                    currentPage = 4,
                    currentState = OnboardingPageState.Page4State()
                )
            }
        }

        reduce { nextPageState ?: state }
    }

    fun navigateToSpotListView() = intent {
        postSideEffect(OnboardingScreenSideEffect.NavigateToSpotListView)
    }
}

//전체 Onboarding 담담 State
data class OnboardingScreenState(
    val currentPage: Int = 1,
    val currentState: OnboardingPageState = OnboardingPageState.Page1State(),
    val showDialog: Boolean = false,
    val totalPages: Int = ONBOARDING_TOTAL_PAGES,
    val onboardingResult: OnboardingResult = OnboardingResult(),
)

sealed class OnboardingPageState {
    data class Page1State(
        val titleNum: Int = 1,
        val pageDescription: Int = R.string.onboarding_1_title,
        val columnSize: Int = 3,
        val foodItems: List<FoodItems> = FoodItems.entries,
        val isNothingClicked: Boolean = false,
        val selectedCard: Set<String> = emptySet(),
    ) : OnboardingPageState()

    data class Page2State(
        val titleNum: Int = 2,
        val pageDescription: Int = R.string.onboarding_2_title,
        val columnSize: Int = 3,
        val foodItems: List<FoodTypeItems> = FoodTypeItems.entries,
        val selectedCard: Set<String> = emptySet() //List였다가 Set으로 바꿔서 담음
    ) : OnboardingPageState()

    data class Page3State(
        val titleNum: Int = 3,
        val pageDescription: Int = R.string.onboarding_3_title,
        val columnSize: Int = 2,
        val placeItems: List<PlaceItems> = PlaceItems.entries,
        val selectedCard: Set<String> = emptySet() // String으로 바꿔서 저장해야 함
    ) : OnboardingPageState()

    data class Page4State(
        val titleNum: Int = 4,
        val pageDescription: Int = R.string.onboarding_4_title,
        val columnSize: Int = 2,
        val placeItems: List<MoodItems> = MoodItems.entries,
        val selectedCard: Set<String> = emptySet() //String으로 바꿔서 저장해야 함
    ) : OnboardingPageState()

    data class Page5State(
        val titleNum: Int = 5,
        val pageDescription: Int = R.string.onboarding_5_title,
        val columnSize: Int = 2,
        val placeItems: List<PreferPlaceItems> = PreferPlaceItems.entries,
        val selectedCard: Set<String> = emptySet() //List였다가 Set으로 바꿔서 담음
    ) : OnboardingPageState()
}

sealed interface OnboardingScreenSideEffect {
    data object NavigateToLoadingPage: OnboardingScreenSideEffect
    data object NavigateToSpotListView: OnboardingScreenSideEffect
}

@Parcelize
@Serializable
data class OnboardingResult(
    val dislikeFoodList: Set<String> = emptySet(),
    val favoriteCuisineRank: List<String> = emptyList(),
    val favoriteSpotType: String = "",
    val favoriteSpotStyle: String = "",
    val favoriteSpotRank: List<String> = emptyList()
) : Parcelable
