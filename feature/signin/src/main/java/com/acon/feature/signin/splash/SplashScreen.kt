package com.acon.feature.signin.splash

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.acon.core.designsystem.theme.AconTheme
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun SplashScreen(
    state: SplashUiState,
    onAnimationEnd: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler(enabled = true) {}

    when(state) {
        SplashUiState.StartSplash -> {
            Column (
                modifier = modifier
                    .background(AconTheme.color.Gray9),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val composition by rememberLottieComposition(
                    LottieCompositionSpec.Asset("acon_splash_lottie.json")
                )
                val logoAnimationState = animateLottieCompositionAsState(composition = composition)
                LottieAnimation(
                    composition = composition,
                    progress = { logoAnimationState.progress }
                )
                if (logoAnimationState.isAtEnd && logoAnimationState.isPlaying) {
                    onAnimationEnd()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SplashScreenPreview() {
    AconTheme {
        SplashScreen(
            state = SplashUiState.StartSplash,
            onAnimationEnd = {},
        )
    }
}