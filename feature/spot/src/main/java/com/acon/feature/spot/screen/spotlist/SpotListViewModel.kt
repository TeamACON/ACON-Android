package com.acon.feature.spot.screen.spotlist

import android.location.Location
import androidx.compose.runtime.Immutable
import com.acon.core.utils.feature.base.BaseContainerHost
import com.acon.domain.model.spot.Condition
import com.acon.domain.model.spot.Spot
import com.acon.domain.repository.SpotRepository
import com.acon.domain.type.OptionType
import com.acon.domain.type.SpotType
import com.acon.feature.spot.BuildConfig
import com.acon.feature.spot.mock.spotListMock
import com.acon.feature.spot.type.AvailableWalkingTimeType
import com.acon.feature.spot.type.CafePriceRangeType
import com.acon.feature.spot.type.RestaurantPriceRangeType
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

    private var debugRefresher = 0
    fun onLocationReady(location: Location) = intent {
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
            delay(1000)
            reduce {
                if (BuildConfig.DEBUG)
                    (state as? SpotListUiState.Success)?.copy(
                        spotList = spotListMock,
                        isRefreshing = false
                    ) ?: SpotListUiState.Success(
                        spotList = spotListMock,
                        isRefreshing = false
                    )

                else SpotListUiState.LoadFailed
            }
        }

        runOn<SpotListUiState.Success> {
            reduce {
                state.copy()
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

@Immutable
data class ConditionState(
    val spotType: SpotType,
    val restaurantFeatureOptionType: List<OptionType.RestaurantFeatureOptionType>,
    val companionTypeOptionType: List<OptionType.CompanionTypeOptionType>,
    val cafeFeatureOptionType: List<OptionType.CafeFeatureOptionType>,
    val visitPurposeOptionType: List<OptionType.VisitPurposeOptionType>,
    val restaurantWalkingTime: AvailableWalkingTimeType,
    val cafeWalkingTime: AvailableWalkingTimeType,
    val restaurantPriceRange: RestaurantPriceRangeType,
    val cafePriceRange: CafePriceRangeType
)

sealed interface SpotListSideEffect {
    data class NavigateToSpotDetail(val id: Int) : SpotListSideEffect
}