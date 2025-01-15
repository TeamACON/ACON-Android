package com.acon.feature.spot.screen.spotlist

import com.acon.core.utils.feature.base.BaseContainerHost
import com.acon.domain.error.spot.FetchSpotListError
import com.acon.domain.model.spot.Condition
import com.acon.domain.model.spot.Spot
import com.acon.domain.repository.SpotRepository
import com.acon.domain.type.SpotType
import com.acon.feature.spot.mock.spotListMock
import com.acon.feature.spot.type.SpotShowType
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SpotListViewModel @Inject constructor(
    private val spotRepository: SpotRepository
) : BaseContainerHost<SpotListUiState, SpotListSideEffect>() {

    override val container = container<SpotListUiState, SpotListSideEffect>(SpotListUiState.Loading) {

    }

    // TODO : Parameters
    fun onRefresh(latitude: Double, longitude: Double) = intent {
        reduce {
            SpotListUiState.Success(spotListMock, SpotShowType.BEST1)
        }
//        spotRepository.fetchSpotList(
//            latitude = latitude,
//            longitude = longitude,
//            condition = Condition(
//                spotType = SpotType.RESTAURANT,
//                filterList = emptyList()
//            ),
//            walkingTime = 10,
//            priceRange = 10000
//        ).onSuccess {
//            reduce { SpotListUiState.Success(it, SpotShowType.BEST1) }
//        }.onFailure {
//            reduce { SpotListUiState.LoadFailed }
//        }
    }
}

sealed interface SpotListUiState {
    data class Success(val spotList: List<Spot>, val spotShowType: SpotShowType) : SpotListUiState
    data object Loading : SpotListUiState
    data object LoadFailed: SpotListUiState
}

sealed interface SpotListSideEffect {
    data object NavigateToSpotDetail : SpotListSideEffect
}