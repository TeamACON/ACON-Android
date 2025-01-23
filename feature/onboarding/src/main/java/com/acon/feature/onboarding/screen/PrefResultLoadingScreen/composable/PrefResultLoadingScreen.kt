package com.acon.feature.onboarding.screen.PrefResultLoadingScreen.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Composition
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.acon.core.designsystem.theme.AconTheme
import com.acon.feature.onboarding.R
import com.acon.feature.onboarding.screen.PrefResultLoadingScreen.PrefResultLoadingScreenSideEffect
import com.acon.feature.onboarding.screen.PrefResultLoadingScreen.PrefResultLoadingScreenState
import com.acon.feature.onboarding.screen.PrefResultLoadingScreen.PrefResultLoadingScreenViewModel
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieAnimatable
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun PrefResultLoadingScreenContainer(
    modifier: Modifier = Modifier,
    viewModel: PrefResultLoadingScreenViewModel = hiltViewModel(),
    navigateToSpotListView: () -> Unit = {},
){
    val state = viewModel.collectAsState().value

    viewModel.collectSideEffect {
        when(it) {
            PrefResultLoadingScreenSideEffect.navigateToSpotListView -> {
                navigateToSpotListView()
            }
        }
    }

    PrefResultLoadingScreen(
        modifier = modifier,
        screenState = state,
        postOnboardingResult = viewModel::postOnboardingResult,
        navigateToSpotListView = navigateToSpotListView,
    )
}

@Composable
fun PrefResultLoadingScreen(
    modifier: Modifier = Modifier,
    screenState: PrefResultLoadingScreenState,
    postOnboardingResult: () -> Unit = {},
    navigateToSpotListView: () -> Unit = {},
){
    LaunchedEffect(Unit) {
        postOnboardingResult()
    }

    var lottieRes : Int
    var loadingText : String

    when (screenState) {
        is PrefResultLoadingScreenState.Loading -> {
            lottieRes = R.raw.loading_lottie
            loadingText = stringResource(R.string.onboarding_6_title)

            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(lottieRes))

            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(color = AconTheme.color.Gray9),
                contentAlignment = Alignment.Center
            ){

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = loadingText,
                        style = AconTheme.typography.head4_24_sb,
                        color = AconTheme.color.White,
                        textAlign = TextAlign.Center
                    )
                    LottieAnimation(
                        composition = composition,
                        iterations = LottieConstants.IterateForever,
                        modifier = Modifier
                            .padding(vertical = 40.dp)
                            .aspectRatio(1.5f),
                    )
                }
            }
        }

        PrefResultLoadingScreenState.LoadSucceed -> {
            lottieRes = R.raw.loading_complete_lottie
            loadingText = stringResource(R.string.onboarding_6_title2)

            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(lottieRes))

            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(color = AconTheme.color.Gray9),
                contentAlignment = Alignment.Center
            ){

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = loadingText,
                        style = AconTheme.typography.head4_24_sb,
                        color = AconTheme.color.White,
                        textAlign = TextAlign.Center
                    )
                    LottieAnimation(
                        composition = composition,
                        iterations = 1,
                        modifier = Modifier
                            .padding(vertical = 40.dp)
                            .aspectRatio(1.5f),
                    )
                }
            }
        }

        PrefResultLoadingScreenState.LoadFailed -> {
            Text(text = "load failed")
        }
    }
}

@Composable
@Preview
private fun PreviewOnboardingScreen6(){
    PrefResultLoadingScreenContainer()
}