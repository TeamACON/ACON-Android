package com.acon.feature.onboarding.screen.PrefResultLoadingScreen.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading_lottie))
    //val composition by rememberLottieComposition(LottieCompositionSpec.Asset("drawable/loading_lottie.json"))

    val lottieAnimatable = rememberLottieAnimatable()

    LaunchedEffect(composition) {
        lottieAnimatable.animate(
            composition = composition,
            clipSpec = LottieClipSpec.Frame(0, 1200),
            initialProgress = 0f
        )
    }

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
        composition = composition,
        lottieAnimatable = lottieAnimatable,
    )
}

@Composable
fun PrefResultLoadingScreen(
    modifier: Modifier = Modifier,
    screenState: PrefResultLoadingScreenState,
    navigateToSpotListView: () -> Unit = {},
    composition: LottieComposition?,
    lottieAnimatable: LottieAnimatable
){
    //로딩이 다 되면 navigateToSpotListView 로 보내야 하는데 이걸 어떻게 하지?

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = AconTheme.color.Gray9)
            .padding(30.dp)
    ){
        val loadingText = if (screenState.isLoading) "회원님의 취향을\n빠르게 분석하고 있어요."
                            else "분석이 완료되었어요!\n추천 맛집을 보여드릴게요."

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.align(Alignment.Center)
            ){
                Text(
                    text = loadingText,
                    style = AconTheme.typography.head6_20_sb,
                    color = AconTheme.color.White,
                    textAlign = TextAlign.Center
                )
                LottieAnimation(
                    composition = composition,
                    //progress = lottieAnimatable,
                    contentScale = ContentScale.FillHeight,
                )
            }
        }
    }
}

@Composable
@Preview
private fun PreviewOnboardingScreen6(){
    PrefResultLoadingScreenContainer()
}