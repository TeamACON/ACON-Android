package com.acon.feature.profile.screen.profile.composable.screen

import androidx.compose.runtime.Immutable
import com.acon.core.utils.feature.base.BaseContainerHost
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@OptIn(OrbitExperimental::class)
@HiltViewModel
class ProfileViewModel @Inject constructor(

) : BaseContainerHost<ProfileUiState, ProfileUiSideEffect>() {

    override val container =
        container<ProfileUiState, ProfileUiSideEffect>(ProfileUiState.LoadFailed()) {

        }

    // TODO - 로그인 추가해야 함
    // TODO - 프로필 정보 조회 추가해야 함

    fun onSettings() = intent {
        postSideEffect(ProfileUiSideEffect.OnNavigateToSettingsScreen)
    }

    fun onEditProfile() = intent {
        postSideEffect(ProfileUiSideEffect.OnNavigateToProfileEditScreen)
    }

    fun onGoogleSignIn() = intent {
        postSideEffect(ProfileUiSideEffect.OnNavigateToSignInScreen)
    }

    fun onTermOfUse() = intent {
        postSideEffect(ProfileUiSideEffect.OnTermOfUse)
    }

    fun onPrivatePolicy() = intent {
        postSideEffect(ProfileUiSideEffect.OnPrivatePolicy)
    }

    fun onBottomSheetShowStateChange(show: Boolean) = intent {
        runOn<ProfileUiState.LoadFailed> { // TODO - GUSET 일 때 (추후 수정 필요)
            reduce {
                state.copy(showLoginBottomSheet = show)
            }
        }
    }
}

sealed interface ProfileUiState {
    @Immutable
    data class Success(
        val profileImage: String,
        val aconCount: Int,
        val verifiedArea: String,
    ) : ProfileUiState

    data object Loading : ProfileUiState
    data class LoadFailed(
        val showLoginBottomSheet: Boolean = false
    ) : ProfileUiState
}

sealed interface ProfileUiSideEffect {
    data object OnNavigateToSettingsScreen : ProfileUiSideEffect
    data object OnNavigateToProfileEditScreen : ProfileUiSideEffect
    data object OnNavigateToSignInScreen : ProfileUiSideEffect
    data object OnTermOfUse : ProfileUiSideEffect
    data object OnPrivatePolicy : ProfileUiSideEffect
}
