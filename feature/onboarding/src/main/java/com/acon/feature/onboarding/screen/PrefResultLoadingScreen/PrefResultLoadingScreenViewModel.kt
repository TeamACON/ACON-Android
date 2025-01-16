package com.acon.feature.onboarding.screen.PrefResultLoadingScreen

import com.acon.core.utils.feature.base.BaseContainerHost
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class PrefResultLoadingScreenViewModel @Inject constructor(
) : BaseContainerHost<PrefResultLoadingScreenState, PrefResultLoadingScreenSideEffect>() {

    override val container: Container<PrefResultLoadingScreenState, PrefResultLoadingScreenSideEffect> =
        container(
            initialState = PrefResultLoadingScreenState(isLoading = true)
        )

    fun navigateToSpotListView() = intent {
        postSideEffect(PrefResultLoadingScreenSideEffect.navigateToSpotListView)
    }
}

data class PrefResultLoadingScreenState(
    val isLoading : Boolean = true
)

sealed interface PrefResultLoadingScreenSideEffect {
    data object navigateToSpotListView: PrefResultLoadingScreenSideEffect
}