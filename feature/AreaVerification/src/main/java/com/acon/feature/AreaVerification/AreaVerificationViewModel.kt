package com.acon.feature.AreaVerification

import android.content.Context
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AreaVerificationViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val _uiState = MutableStateFlow(AreaVerificationUiState())
    val uiState: StateFlow<AreaVerificationUiState> = _uiState.asStateFlow()

    fun onNewLocationSelected() {
        _uiState.update { currentState ->
            currentState.copy(
                isNewLocationSelected = !currentState.isNewLocationSelected,
                isButtonEnabled = !currentState.isNewLocationSelected
            )
        }
    }

    fun updateShowPermissionDialog(show: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(showPermissionDialog = show)
        }
    }

    fun onNextButtonClicked() {
        _uiState.update { currentState ->
            currentState.copy(
                isButtonEnabled = false
            )
        }
        // TODO: Add logic
    }
}