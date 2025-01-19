package com.acon.feature.upload

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class UploadViewModel @Inject constructor() : ViewModel(),
    ContainerHost<UploadState, UploadSideEffect> {
    override val container = container<UploadState, UploadSideEffect>(UploadState())

    fun onIntent(intent: UploadIntent) {
        when (intent) {
            is UploadIntent.SelectLocation -> {
                intent {
                    reduce { state.copy(selectedLocation = intent.location) }
                }
            }

            is UploadIntent.OnNextStep -> {
                intent {
                    reduce { state.copy(currentStep = UploadStep.DOTORI_REVIEW) }
                }
            }

            is UploadIntent.SelectDotori -> selectDotori(intent.index)
            is UploadIntent.DeselectDotori -> deselectDotori(intent.index)
            is UploadIntent.NavigateBack -> navigateBack()
            is UploadIntent.UploadDotori -> uploadDotori()
        }
    }

    private fun selectDotori(index: Int) = intent {
        if (index >= state.selectedCount && index < state.maxCount) {
            reduce {
                state.copy(animatingIndex = null)
            }

            delay(50)
            reduce {
                state.copy(
                    selectedCount = index + 1,
                    isButtonEnabled = true,
                    animatingIndex = index
                )
            }

            delay(3000)
            reduce {
                if (state.animatingIndex == index) {
                    state.copy(animatingIndex = null)
                } else {
                    state
                }
            }
        }
    }

    private fun deselectDotori(index: Int) = intent {
        if (state.selectedCount > 0) {
            reduce {
                state.copy(animatingIndex = null)
            }

            delay(50)

            reduce {
                state.copy(
                    selectedCount = index + 1,
                    isButtonEnabled = true,
                    animatingIndex = index
                )
            }

            delay(3000)
            reduce {
                if (state.animatingIndex == index) {
                    state.copy(animatingIndex = null)
                } else {
                    state
                }
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
