package com.acon.feature.spot.screen.spotlist

import com.acon.core.utils.feature.base.BaseContainerHost
import com.acon.domain.model.spot.Condition
import com.acon.domain.model.spot.Spot
import com.acon.domain.repository.SpotRepository
import com.acon.domain.type.SpotType
import com.acon.feature.spot.BuildConfig
import com.acon.feature.spot.mock.spotListMock1
import com.acon.feature.spot.mock.spotListMock2
import com.acon.feature.spot.type.SpotShowType
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.chrisbanes.haze.HazeState
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


    private var debugRefresher = 0
    fun onLocationReady(latitude: Double, longitude: Double) = intent {
        spotRepository.fetchSpotList(
            latitude = latitude,
            longitude = longitude,
            condition = Condition(
                spotType = SpotType.RESTAURANT,
                filterList = emptyList()
            ),
            walkingTime = 10,
            priceRange = 10000
        ).onSuccess {
            reduce { SpotListUiState.Success(it, SpotShowType.BEST1, false) }
        }.onFailure {
            delay(1000)
            reduce {
                if (BuildConfig.DEBUG)
                    SpotListUiState.Success(
                        if (debugRefresher % 2 == 0) spotListMock1 else spotListMock2,
                        if (debugRefresher++ % 2 == 0) SpotShowType.BEST1 else SpotShowType.BEST2,
                        false
                    )
                else SpotListUiState.LoadFailed
            }
        }
    }

    // TODO : Parameters
    fun onRefresh(latitude: Double, longitude: Double) = intent {
        runOn<SpotListUiState.Success> {
            reduce {
                state.copy(isRefreshing = true)
            }
            onLocationReady(latitude, longitude)
        }
    }

    fun onFilterBottomSheetStateChange(show: Boolean) = intent {
        runOn<SpotListUiState.Success> {
            reduce {
                state.copy(showFilterBottomSheet = show)
            }
        }
    }
}

sealed interface SpotListUiState {
    data class Success(
        val spotList: List<Spot>,
        val spotShowType: SpotShowType,
        val isRefreshing: Boolean = false,
        val hazeState: HazeState = HazeState(),
        val currentCondition: Condition? = null,
        val showFilterBottomSheet: Boolean = false
    ) : SpotListUiState
    data object Loading : SpotListUiState
    data object LoadFailed: SpotListUiState
}

sealed interface SpotListSideEffect {
    data object NavigateToSpotDetail : SpotListSideEffect
}