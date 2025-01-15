package com.acon.feature.signin.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SignInScreenContent(
    modifier: Modifier = Modifier
) {
    SignInScreen(
        navigateToSpotLisView = {},
        onClickTermsOfUse = {},
        onClickPrivacyPolicy = {},
        onClickLoginGoogle = {}
    )
}