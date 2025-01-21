package com.acon.feature.spot.screen.spotdetail

import android.location.Location
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.acon.core.utils.feature.base.BaseContainerHost
import com.acon.domain.model.spot.SpotDetailInfo
import com.acon.domain.model.spot.SpotDetailMenu
import com.acon.domain.repository.SpotRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.viewmodel.container
import javax.annotation.concurrent.Immutable
import javax.inject.Inject

@HiltViewModel
class SpotDetailViewModel @Inject constructor(
    private val spotRepository: SpotRepository,
    savedStateHandle: SavedStateHandle
) : BaseContainerHost<SpotDetailUiState, SpotDetailSideEffect>() {

    // TODO - SpotListView에서 spotId 받아야 함
    // val spotId = savedStateHandle.get<Long>("spotId")

    override val container =
        container<SpotDetailUiState, SpotDetailSideEffect>(SpotDetailUiState.Loading) {
            val spotDetailInfoDeferred = viewModelScope.async {
                spotRepository.getSpotDetailInfo(1)
            }
            val spotDetailMenuDeferred = viewModelScope.async {
                spotRepository.getSpotMenuList(1)
            }

            val spotDetailInfoResult = spotDetailInfoDeferred.await()
            val spotDetailMenuResult = spotDetailMenuDeferred.await()

            reduce {
                if (spotDetailInfoResult.getOrNull() == null) {
                    SpotDetailUiState.LoadFailed
                }
                else if (spotDetailMenuResult.getOrNull() == null) {
                    SpotDetailUiState.LoadFailed
                }
                else {
                    SpotDetailUiState.Success(
                        spotDetailInfo = spotDetailInfoResult.getOrNull()!!,
                        spotDetailMenuList = spotDetailMenuResult.getOrNull()!!
                    )
                }
            }
        }

    fun navigateToSpotListView() = intent {
        postSideEffect(
            SpotDetailSideEffect.NavigateToSpotListView
        )
    }

    @OptIn(OrbitExperimental::class)
    fun onFindWay(location: Location) = intent {
        runOn<SpotDetailUiState.Success> {
            postSideEffect(
                SpotDetailSideEffect.OnFindWayButtonClick(
                    destinationLat = state.spotDetailInfo.latitude,
                    destinationLng = state.spotDetailInfo.longitude,
                    destinationName = state.spotDetailInfo.name,
                    location = location
                )
            )
        }
    }
}

sealed interface SpotDetailUiState {
    @Immutable
    data class Success(
        val spotDetailInfo: SpotDetailInfo,
        val spotDetailMenuList: List<SpotDetailMenu>,
    ) : SpotDetailUiState

    data object Loading : SpotDetailUiState
    data object LoadFailed : SpotDetailUiState
}

sealed interface SpotDetailSideEffect {
    data object NavigateToSpotListView : SpotDetailSideEffect
    data class OnFindWayButtonClick(
        val destinationLat: Double,
        val destinationLng: Double,
        val destinationName: String,
        val location: Location
    ) : SpotDetailSideEffect
}