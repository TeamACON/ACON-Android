package com.acon.feature.signin.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.acon.domain.repository.GoogleTokenRepository

@Composable
fun SignInScreenContent(
    modifier: Modifier = Modifier,
    googleSignInRepository: GoogleTokenRepository,
    viewModel: SignInViewModel = hiltViewModel()
    ) {
    SignInScreen(
        navigateToSpotLisView = {},
        onClickTermsOfUse = {},
        onClickPrivacyPolicy = {},
        onClickLoginGoogle = {
            viewModel.googleLogin(googleSignInRepository)
        }
    )
}