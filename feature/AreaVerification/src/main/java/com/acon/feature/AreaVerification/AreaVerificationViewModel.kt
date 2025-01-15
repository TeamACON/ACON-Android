package com.acon.feature.AreaVerification

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AreaVerificationViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(AreaVerificationUiState())
    val uiState: StateFlow<AreaVerificationUiState> = _uiState.asStateFlow()

    fun onNewLocationSelected() {
        _uiState.update { it.copy(isNewLocationSelected = true) }
    }

    fun updateLocation(latitude: Double, longitude: Double) {
        _uiState.update {
            it.copy(
                latitude = latitude,
                longitude = longitude,
                isLocationObtained = true
            )
        }
    }

    fun onNextButtonClicked() {
        _uiState.update { currentState ->
            currentState.copy(
                isButtonEnabled = false
            )
        }
    }

    fun updateShowPermissionDialog(show: Boolean) {
        _uiState.update { it.copy(showPermissionDialog = show) }
    }
}
