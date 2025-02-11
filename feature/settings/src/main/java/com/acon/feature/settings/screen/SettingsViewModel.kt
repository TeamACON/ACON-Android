package com.acon.feature.settings.screen

import com.acon.core.utils.feature.base.BaseContainerHost
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    // TODO - 로그아웃 추가 해야 함
) : BaseContainerHost<SettingsUiState, SettingsSideEffect>() {

    override val container =
        container<SettingsUiState, SettingsSideEffect>(SettingsUiState.Default) { }

    // TODO - 로그아웃 추가 해야 함

    fun navigateBack() = intent {
        postSideEffect(SettingsSideEffect.NavigateToProfile)
    }

    fun onUpdateVersion() = intent {
        postSideEffect(SettingsSideEffect.OpenPlayStore)
    }

    fun onTermOfUse() = intent {
        postSideEffect(SettingsSideEffect.OpenTermOfUse)
    }

    fun onPrivatePolicy() = intent {
        postSideEffect(SettingsSideEffect.OpenPrivatePolicy)
    }

    fun onRetryOnBoarding() = intent {
        postSideEffect(SettingsSideEffect.NavigateToOnboarding)
    }

    fun onSignOut() = intent {
        postSideEffect(SettingsSideEffect.NavigateToSignIn)
    }

    fun onDeleteAccount() = intent {
        postSideEffect(SettingsSideEffect.NavigateToDeleteAccount)
    }
}

sealed interface SettingsUiState{
    // TODO - 로그인 여부 상태 변수 필요
    data object Default : SettingsUiState
}

sealed interface SettingsSideEffect {
    data object NavigateToProfile : SettingsSideEffect
    data object OpenPlayStore : SettingsSideEffect
    data object OpenTermOfUse : SettingsSideEffect
    data object OpenPrivatePolicy : SettingsSideEffect
    data object NavigateToOnboarding : SettingsSideEffect
    data object NavigateToSignIn : SettingsSideEffect
    data object NavigateToDeleteAccount : SettingsSideEffect
}