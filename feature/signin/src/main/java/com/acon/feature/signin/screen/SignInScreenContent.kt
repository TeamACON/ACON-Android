package com.acon.feature.signin.screen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.acon.domain.repository.SocialRepository
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun SignInScreenContent(
    socialRepository: SocialRepository,
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
            is SignInSideEffect.NavigateToSpotListView -> { navigateToSpotListView() }
            is SignInSideEffect.NavigateToAreaVerification -> { navigateToAreaVerification() }
            is SignInSideEffect.OnClickTermsOfUse -> {
                val url = "https://bit.ly/4jq9D88"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context.startActivity(intent)
            }
            is SignInSideEffect.OnClickPrivacyPolicy -> {
                val url = "https://bit.ly/40qFrkz"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context.startActivity(intent)
            }
        }
    }

}