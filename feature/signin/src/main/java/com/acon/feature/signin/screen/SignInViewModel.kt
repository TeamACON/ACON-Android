package com.acon.feature.signin.screen

import com.acon.core.utils.feature.base.BaseContainerHost
import com.acon.domain.repository.GoogleTokenRepository
import com.acon.domain.repository.TokenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val tokenRepository: TokenRepository
) : BaseContainerHost<SignInUiState, SignInSideEffect>() {

    override val container: Container<SignInUiState, SignInSideEffect> =
        container(initialState = SignInUiState.Loading) {
            isTokenValid()
        }

    fun googleLogin(googleTokenRepository: GoogleTokenRepository) = intent {
        googleTokenRepository.signIn()
            .onSuccess {
                reduce {
                    SignInUiState.Success
                }
                postSideEffect(SignInSideEffect.NavigateToAreaVerification)
            }.onFailure  {
                reduce {
                    SignInUiState.LoadFailed
                }
            }
    }

    private fun isTokenValid() = intent {
        tokenRepository.getAccessToken().onSuccess { accessToken ->
            if (!accessToken.isNullOrEmpty()) {
                postSideEffect(SignInSideEffect.NavigateToSpotListView)
            }
        }
    }

    fun navigateToSpotListView() = intent {
        postSideEffect(
            SignInSideEffect.NavigateToSpotListView
        )
    }

    fun onClickTermsOfUse() = intent {
        postSideEffect(
            SignInSideEffect.OnClickTermsOfUse
        )
    }

    fun onClickPrivacyPolicy() = intent {
        postSideEffect(
            SignInSideEffect.OnClickPrivacyPolicy
        )
    }

}

sealed interface SignInUiState {
    data object Success: SignInUiState
    data object Loading : SignInUiState
    data object LoadFailed: SignInUiState
}

sealed interface SignInSideEffect {
    data object NavigateToSpotListView : SignInSideEffect
    data object NavigateToAreaVerification: SignInSideEffect
    data object OnClickTermsOfUse : SignInSideEffect
    data object OnClickPrivacyPolicy : SignInSideEffect
}
