package com.acon.feature.signin.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.acon.domain.repository.GoogleTokenRepository

@Composable
fun SignInScreenContent(
    navigateToSpotListView: () -> Unit,
    navigateToAreaVerification: () -> Unit,
    modifier: Modifier = Modifier,
    googleSignInRepository: GoogleTokenRepository,
    viewModel: SignInViewModel = hiltViewModel()
    ) {
    SignInScreen(
        navigateToSpotListView = {},
        navigateToAreaVerification = {},
        onClickTermsOfUse = {},
        onClickPrivacyPolicy = {},
        onClickLoginGoogle = {
            viewModel.googleLogin(googleSignInRepository)
        }
    )
}