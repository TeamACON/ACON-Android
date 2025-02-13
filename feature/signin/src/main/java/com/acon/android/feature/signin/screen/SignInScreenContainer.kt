package com.acon.android.feature.signin.screen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.acon.android.core.utils.feature.constants.AppURL
import com.acon.android.domain.repository.SocialRepository
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun SignInScreenContainer(
    socialRepository: SocialRepository,
    navigateToSignInScreen: () -> Unit,
    navigateToSpotListView: () -> Unit,
    navigateToAreaVerification: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state by viewModel.collectAsState()

    SignInScreen(
        state = state,
        modifier = modifier.fillMaxSize(),
        navigateToSpotListView = viewModel::navigateToSpotListView,
        onClickTermsOfUse = viewModel::onClickTermsOfUse,
        onClickPrivacyPolicy = viewModel::onClickPrivacyPolicy,
        onClickLoginGoogle = {
            viewModel.googleLogin(socialRepository)
        }
    )

    viewModel.collectSideEffect { sideEffect ->
        when(sideEffect) {
            is SignInSideEffect.NavigateToSignInScreen -> { navigateToSignInScreen() }
            is SignInSideEffect.NavigateToSpotListView -> { navigateToSpotListView() }
            is SignInSideEffect.NavigateToAreaVerification -> { navigateToAreaVerification() }
            is SignInSideEffect.OnClickTermsOfUse -> {
                val url = AppURL.TERM_OF_USE
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context.startActivity(intent)
            }
            is SignInSideEffect.OnClickPrivacyPolicy -> {
                val url = AppURL.PRIVATE_POLICY
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context.startActivity(intent)
            }
        }
    }

}