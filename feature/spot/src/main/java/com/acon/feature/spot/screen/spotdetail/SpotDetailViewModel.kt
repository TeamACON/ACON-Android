package com.acon.feature.spot.screen.spotdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.acon.core.utils.feature.base.BaseContainerHost
import com.acon.domain.model.spot.SpotDetailInfo
import com.acon.domain.model.spot.SpotDetailMenu
import com.acon.domain.repository.SpotRepository
import kotlinx.coroutines.async
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

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
}

sealed interface SpotDetailUiState {
    data class Success(
        val spotDetailInfo: SpotDetailInfo,
        val spotDetailMenuList: List<SpotDetailMenu>,
    ) : SpotDetailUiState

    data object Loading : SpotDetailUiState
    data object LoadFailed : SpotDetailUiState
}

sealed interface SpotDetailSideEffect {
    data object NavigateToSpotListView : SpotDetailSideEffect
    data object NavigateToAreaVerification : SpotDetailSideEffect
}