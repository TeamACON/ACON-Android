package com.acon.feature.signin.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.acon.core.designsystem.component.button.AconGoogleLoginButton
import com.acon.core.designsystem.theme.AconTheme
import com.acon.feature.signin.R
import com.acon.feature.signin.screen.component.SignInTopBar

@Composable
fun SignInScreen(
    navigateToSpotListView: () -> Unit,
    navigateToAreaVerification: () -> Unit,
    onClickTermsOfUse: () -> Unit,
    onClickPrivacyPolicy: () -> Unit,
    onClickLoginGoogle: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AconTheme.color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SignInTopBar(onClickText = navigateToSpotListView)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .padding(bottom = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.signin_logo_title),
                style = AconTheme.typography.subtitle2_14_med,
                textAlign = TextAlign.Center,
                color = AconTheme.color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                imageVector = ImageVector.vectorResource(com.acon.core.designsystem.R.drawable.ic_word_logo_splash_org_78),
                contentDescription = stringResource(R.string.signin_logo_title_content_description)
            )
        }

        AconGoogleLoginButton(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 16.dp),
            onClick = onClickLoginGoogle
        )
        Text(
            text = stringResource(R.string.signin_policy),
            style = AconTheme.typography.cap1_11_reg,
            color = AconTheme.color.Gray3,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 8.dp)
        )
        Row(
            modifier = Modifier
                .padding(bottom = 76.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.signin_terms_of_service),
                style = AconTheme.typography.cap1_11_reg,
                color = AconTheme.color.Gray5,
                textAlign = TextAlign.Center,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .clickable { onClickTermsOfUse() }
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = stringResource(R.string.signin_privacy_policy),
                style = AconTheme.typography.cap1_11_reg,
                color = AconTheme.color.Gray5,
                textAlign = TextAlign.Center,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .clickable { onClickPrivacyPolicy() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSignInScreen() {
    AconTheme {
        SignInScreen(
            navigateToSpotListView = {},
            navigateToAreaVerification = {},
            onClickLoginGoogle = {},
            onClickTermsOfUse = {},
            onClickPrivacyPolicy = {}
        )
    }
}