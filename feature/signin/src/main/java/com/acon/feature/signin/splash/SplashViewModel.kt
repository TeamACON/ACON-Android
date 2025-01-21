package com.acon.feature.signin.splash

import com.acon.core.utils.feature.base.BaseContainerHost
import com.acon.domain.repository.TokenLocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val tokenLocalRepository: TokenLocalRepository
) : BaseContainerHost<SplashUiState, SplashSideEffect>() {

    override val container = container<SplashUiState, SplashSideEffect>(SplashUiState.StartSplash) {}

     fun isTokenValid() = intent {
         tokenLocalRepository.getAccessToken().onSuccess { accessToken ->
             if (!accessToken.isNullOrEmpty()) {
                 postSideEffect(SplashSideEffect.NavigationToSpotListView)
             } else {
                 postSideEffect(SplashSideEffect.NavigationToSignIn)
             }
         }.onFailure {
             postSideEffect(SplashSideEffect.NavigationToSignIn)
         }
    }

}

sealed interface SplashUiState {
    data object StartSplash: SplashUiState
}
sealed interface SplashSideEffect {
    data object NavigationToSignIn : SplashSideEffect
    data object NavigationToSpotListView : SplashSideEffect
}