package com.acon.feature.areaverification

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class AreaVerificationViewModel @Inject constructor() : ViewModel(),
    ContainerHost<AreaVerificationState, AreaVerificationSideEffect> {

    override val container = container<AreaVerificationState, AreaVerificationSideEffect>(AreaVerificationState())

    fun onNewLocationSelected() = intent {
        reduce {
            state.copy(isNewLocationSelected = true)
        }
    }

    fun updateShowPermissionDialog(show: Boolean) = intent {
        reduce {
            state.copy(showPermissionDialog = show)
        }
    }

    fun onNextButtonClick() = intent {
        if (state.isNewLocationSelected) {
            postSideEffect(AreaVerificationSideEffect.NavigateToNextScreen(state.latitude, state.longitude))
        }
    }

    fun onPermissionSettingClick(packageName: String) = intent {
        postSideEffect(AreaVerificationSideEffect.NavigateToSettings(packageName))
        reduce {
            state.copy(showPermissionDialog = false)
        }
    }

    fun checkLocationAndNavigate() = intent {
        if (state.isLocationObtained) {
            postSideEffect(AreaVerificationSideEffect.NavigateToNewArea(state.latitude, state.longitude))
        }
    }
}
