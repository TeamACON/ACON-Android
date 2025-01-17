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
        navigateToSpotListView = navigateToSpotListView,
    )
}

@Composable
fun PrefResultLoadingScreen(
    modifier: Modifier = Modifier,
    screenState: PrefResultLoadingScreenState,
    navigateToSpotListView: () -> Unit = {},
){

    //여기로 넘어오는 버튼 누르는 순간 POST 요청 날리고
    //응답을 기다리는 순간부터 이 화면으로 넘어옴.
    //200 OK response가 오면 2초 동안 바뀐 로띠 보여주고 navigateToSpotListView 로 이동시키기.

    val lottieRes = if (screenState.isLoading) R.raw.loading_lottie else R.raw.loading_complete_lottie
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(lottieRes))

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = AconTheme.color.Gray9),
        contentAlignment = Alignment.Center
    ){
        val loadingText = if (screenState.isLoading) "회원님의 취향을\n빠르게 분석하고 있어요."
                            else "분석이 완료되었어요!\n추천 맛집을 보여드릴게요."
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = loadingText,
                style = AconTheme.typography.head6_20_sb,
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

@Composable
@Preview
private fun PreviewOnboardingScreen6(){
    PrefResultLoadingScreenContainer()
}