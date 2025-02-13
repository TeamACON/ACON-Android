package com.acon.feature.signin.screen

import com.acon.core.utils.feature.base.BaseContainerHost
import com.acon.domain.repository.AuthRepository
import com.acon.domain.repository.SocialRepository
import com.acon.domain.repository.TokenRepository
import com.acon.domain.type.SocialType
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val tokenRepository: TokenRepository,
    private val authRepository: AuthRepository
) : BaseContainerHost<SignInUiState, SignInSideEffect>() {

    override val container: Container<SignInUiState, SignInSideEffect> =
        container(initialState = SignInUiState.Loading) {
            isTokenValid()
        }

    fun googleLogin(socialRepository: SocialRepository) = intent {
        socialRepository.signIn()
            .onSuccess {
                reduce { SignInUiState.Success }
                postSideEffect(SignInSideEffect.NavigateToAreaVerification)
            }.onFailure { error ->
                when (error) {
                    is CancellationException -> {
                        reduce { SignInUiState.Loading }
                    }
                    is NoSuchElementException -> {
                        reduce { SignInUiState.Loading }
                    }
                    is SecurityException -> {
                        reduce { SignInUiState.Loading }
                    }
                    else -> {
                        reduce { SignInUiState.Loading }
                    }
                }
        }
    }


    private fun isTokenValid() = intent {
        tokenRepository.getGoogleIdToken().onSuccess { googleIdToken ->
            if (!googleIdToken.isNullOrEmpty()) {
                authRepository.postLogin(SocialType.GOOGLE, googleIdToken)
                    .onSuccess {
                        postSideEffect(SignInSideEffect.NavigateToSpotListView)
                    }.onFailure {
                        tokenRepository.removeGoogleIdToken()
                    }
            }
        }
    }

    private fun navigateToSignInScreen() = intent {
        postSideEffect(
            SignInSideEffect.NavigateToSignInScreen
        )
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
    data object Success : SignInUiState
    data object Loading : SignInUiState
    data object LoadFailed: SignInUiState
}

sealed interface SignInSideEffect {
    data object NavigateToSignInScreen : SignInSideEffect
    data object NavigateToSpotListView : SignInSideEffect
    data object NavigateToAreaVerification: SignInSideEffect
    data object OnClickTermsOfUse : SignInSideEffect
    data object OnClickPrivacyPolicy : SignInSideEffect
}
