package com.acon.feature.upload

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class UploadViewModel @Inject constructor() : ViewModel(),
    ContainerHost<UploadState, UploadSideEffect> {
    override val container = container<UploadState, UploadSideEffect>(UploadState())

    fun onIntent(intent: UploadIntent) {
        when (intent) {
            is UploadIntent.SelectDotori -> selectDotori()
            is UploadIntent.DeselectDotori -> deselectDotori()
            is UploadIntent.NavigateBack -> navigateBack()
            is UploadIntent.UploadDotori -> uploadDotori()
        }
    }

    private fun selectDotori() = intent {
        if (state.selectedCount < state.maxCount) {
            reduce {
                state.copy(
                    selectedCount = state.selectedCount + 1,
                    isButtonEnabled = state.selectedCount + 1 > 0
                )
            }
        }
    }

    private fun deselectDotori() = intent {
        if (state.selectedCount > 0) {
            reduce {
                state.copy(
                    selectedCount = state.selectedCount - 1,
                    isButtonEnabled = state.selectedCount - 1 > 0
                )
            }
        }
    }

    private fun uploadDotori() = intent {
        postSideEffect(UploadSideEffect.NavigateToSuccess)
    }

    private fun navigateBack() = intent {
        postSideEffect(UploadSideEffect.NavigateBack)
    }
}
