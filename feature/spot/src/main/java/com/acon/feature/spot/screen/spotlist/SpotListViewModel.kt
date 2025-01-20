package com.acon.feature.spot.screen.spotlist

import android.location.Location
import com.acon.core.utils.feature.base.BaseContainerHost
import com.acon.domain.model.spot.Condition
import com.acon.domain.model.spot.Spot
import com.acon.domain.repository.SpotRepository
import com.acon.domain.type.SpotType
import com.acon.feature.spot.BuildConfig
import com.acon.feature.spot.mock.spotListMock
import com.acon.feature.spot.state.ConditionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@OptIn(OrbitExperimental::class)
@HiltViewModel
class SpotListViewModel @Inject constructor(
    private val spotRepository: SpotRepository
) : BaseContainerHost<SpotListUiState, SpotListSideEffect>() {

    override val container =
        container<SpotListUiState, SpotListSideEffect>(SpotListUiState.Loading) {

        }

    fun onLocationFetched(location: Location) = intent {

    }

    fun fetchInitialSpots(location: Location) = intent {
        spotRepository.fetchSpotList(
            latitude = location.latitude,
            longitude = location.longitude,
            condition = Condition(
                spotType = SpotType.RESTAURANT,
                filterList = emptyList()
            ),
            walkingTime = 10,
            priceRange = 10000
        ).onSuccess {
            reduce {
                (state as? SpotListUiState.Success)?.copy(spotList = it)
                    ?: SpotListUiState.Success(it, false)
            }
        }.onFailure {
            if (BuildConfig.DEBUG.not()) {
                reduce {
                    SpotListUiState.LoadFailed
                }
            } else {
                delay(1500)
                reduce {
                    (state as? SpotListUiState.Success)?.copy(
                        spotList = spotListMock,
                        isRefreshing = false
                    ) ?: SpotListUiState.Success(
                        spotList = spotListMock,
                        isRefreshing = false
                    )
                }
            }
        }
    }

    fun onLocationReady(newLocation: Location) = intent {
        runOn<SpotListUiState.Success> {
            spotRepository.fetchSpotList(
                latitude = newLocation.latitude,
                longitude = newLocation.longitude,
                condition = state.currentCondition?.toCondition()!!, // TODO API 논의
                walkingTime = (if (state.currentCondition?.spotType == SpotType.RESTAURANT)
                    state.currentCondition?.restaurantWalkingTime else state.currentCondition?.cafeWalkingTime)!!.value, // TODO API 논의
                priceRange = (if (state.currentCondition?.spotType == SpotType.RESTAURANT)
                    state.currentCondition?.restaurantPriceRange?.value else state.currentCondition?.cafePriceRange?.value)!! // TODO API 논의
            ).onSuccess {
                reduce {
                    (state as? SpotListUiState.Success)?.copy(spotList = it)
                        ?: SpotListUiState.Success(it, false)
                }
            }.onFailure {
                if (BuildConfig.DEBUG.not()) {
                    reduce {
                        SpotListUiState.LoadFailed
                    }
                } else {
                    delay(1500)
                    reduce {
                        (state as? SpotListUiState.Success)?.copy(
                            spotList = spotListMock,
                            isRefreshing = false
                        ) ?: SpotListUiState.Success(
                            spotList = spotListMock,
                            isRefreshing = false
                        )
                    }
                }
            }
        }
    }

    // TODO : Parameters
    fun onRefresh(location: Location) = intent {
        runOn<SpotListUiState.Success> {
            reduce {
                state.copy(isRefreshing = true)
            }
            onLocationReady(location)
        }
    }

    fun onFilterBottomSheetStateChange(show: Boolean) = intent {
        runOn<SpotListUiState.Success> {
            reduce {
                state.copy(showFilterBottomSheet = show)
            }
        }
    }

    fun onResetFilter() = intent {
        runOn<SpotListUiState.Success> {
            reduce {
                state.copy(showFilterBottomSheet = false, currentCondition = null)
            }
        }
    }

    fun onCompleteFilter(condition: ConditionState) = intent {
        runOn<SpotListUiState.Success> {
            reduce {
                state.copy(showFilterBottomSheet = false, currentCondition = condition)
            }
        }
    }

    fun onSpotItemClick(id: Int) = intent {
        postSideEffect(SpotListSideEffect.NavigateToSpotDetail(id))
    }
}

sealed interface SpotListUiState {
    data class Success(
        val spotList: List<Spot>,
        val isRefreshing: Boolean = false,
        val currentCondition: ConditionState? = null,
        val showFilterBottomSheet: Boolean = false
    ) : SpotListUiState
    data object Loading : SpotListUiState
    data object LoadFailed: SpotListUiState
}

sealed interface SpotListSideEffect {
    data class NavigateToSpotDetail(val id: Int) : SpotListSideEffect
}