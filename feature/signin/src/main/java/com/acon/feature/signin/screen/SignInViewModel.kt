package com.acon.feature.signin.screen

import androidx.lifecycle.viewModelScope
import com.acon.core.utils.feature.base.BaseContainerHost
import com.acon.domain.repository.GoogleTokenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(

) : BaseContainerHost<SignInUiState, SignInSideEffect>() {

    override val container: Container<SignInUiState, SignInSideEffect>
    = container(initialState = SignInUiState.Loading)

    fun googleLogin(googleTokenRepository: GoogleTokenRepository) {
        viewModelScope.launch {
            googleTokenRepository.signIn()
        }
    }

}

sealed interface SignInUiState {
//    data class Success(val spotList: List<Spot>, val spotShowType: SpotShowType) : SignInUiState
    data object Loading : SignInUiState
    data object LoadFailed: SignInUiState
}

sealed interface SignInSideEffect {
    data object NavigateToSpotDetail : SignInSideEffect
}
