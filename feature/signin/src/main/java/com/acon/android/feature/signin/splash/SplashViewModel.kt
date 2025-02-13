package com.acon.android.feature.signin.splash

import com.acon.android.core.utils.feature.base.BaseContainerHost
import com.acon.android.domain.repository.TokenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val tokenRepository: TokenRepository
) : BaseContainerHost<SplashUiState, SplashSideEffect>() {

    override val container = container<SplashUiState, SplashSideEffect>(SplashUiState.StartSplash) {}

     fun isTokenValid() = intent {
         tokenRepository.getAccessToken().onSuccess { accessToken ->
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