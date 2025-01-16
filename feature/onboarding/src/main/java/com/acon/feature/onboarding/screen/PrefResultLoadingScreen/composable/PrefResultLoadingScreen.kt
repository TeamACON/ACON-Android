package com.acon.feature.onboarding.screen.PrefResultLoadingScreen.composable

import android.graphics.Paint.Align
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.acon.core.designsystem.theme.AconTheme
import com.acon.feature.onboarding.screen.PrefResultLoadingScreen.PrefResultLoadingScreenState
import com.acon.feature.onboarding.screen.PrefResultLoadingScreen.PrefResultLoadingScreenViewModel
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun PrefResultLoadingScreenContainer(
    modifier: Modifier = Modifier,
    viewModel: PrefResultLoadingScreenViewModel = hiltViewModel(),
){
    val state = viewModel.collectAsState().value

    PrefResultLoadingScreen(
        modifier = modifier,
        screenState = state
    )
}

@Composable
fun PrefResultLoadingScreen(
    modifier: Modifier = Modifier,
    screenState: PrefResultLoadingScreenState,
){
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
            Column {
                Text(
                    text = loadingText,
                    style = AconTheme.typography.head6_20_sb,
                    color = AconTheme.color.White,
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 120.dp),
                    text = "뭐지 겹치는건가?",
                    style = AconTheme.typography.head6_20_sb,
                    color = AconTheme.color.White,
                    textAlign = TextAlign.Center
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