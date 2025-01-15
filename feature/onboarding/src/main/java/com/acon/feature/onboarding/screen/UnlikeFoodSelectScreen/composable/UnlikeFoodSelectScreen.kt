package com.acon.feature.onboarding.screen.UnlikeFoodSelectScreen.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.acon.core.designsystem.component.button.AconFilledLargeButton
import com.acon.core.designsystem.component.dialog.AconTwoButtonDialog
import com.acon.core.designsystem.theme.AconTheme
import com.acon.feature.onboarding.component.FoodGrid
import com.acon.feature.onboarding.component.OnboardingTopBar
import com.acon.feature.onboarding.screen.UnlikeFoodSelectScreen.UnlikeFoodScreenSideEffect
import com.acon.feature.onboarding.screen.UnlikeFoodSelectScreen.UnlikeFoodScreenState
import com.acon.feature.onboarding.screen.UnlikeFoodSelectScreen.UnlikeFoodScreenViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

/*
* TODO:
*  1) 탑바 뒤로가기 아이콘 & 다음 버튼 버그 (없음 누르고 취소하면 안없어짐)
*  4) 다음 버튼 누를 때마다 서버로 넘겨주나? API 명세서 보고 확인해보기
*  8) 마지막에 Content(Container), Screen 구분하기
* */

@Composable
fun UnlikeFoodScreenContainer(
    modifier: Modifier = Modifier,
    viewModel: UnlikeFoodScreenViewModel = hiltViewModel(),
    navigateToNextPage: () -> Unit = {},
    navigateToLastLoadingPage: () -> Unit = {},
){
    val state = viewModel.collectAsState().value

    UnlikeFoodScreen(
        modifier = modifier,
        screenState = state,
        columnSize = 3,
        onCardClicked = viewModel::onCardClicked,
        onSkipClicked = viewModel::showDialog,
        navigateToNextPage = viewModel::navigateToNextPage,
    )

    viewModel.collectSideEffect {
        when(it){
            UnlikeFoodScreenSideEffect.NavigateToNextPage -> {
                navigateToNextPage()
            }
        }
    }

    if (state.openCloseDialog) {
        AconTwoButtonDialog(
            title = "취향분석을 그만둘까요?",
            content = "선호도 조사만이 남아있어요!\n1분 내로 빠르게 끝내실 수 있어요.",
            leftButtonContent = "그만두기",
            rightButtonContent = "계속하기",
            contentImage = 0,
            onDismissRequest = {
                viewModel.hideDialog()
            },
            onClickLeft = { // 그만두기
                navigateToLastLoadingPage()
            },
            onClickRight = { // 계속하기
                viewModel.hideDialog()
            },
            isImageEnabled = false
        )
    }
}

@Composable
fun UnlikeFoodScreen(
    modifier: Modifier = Modifier,
    screenState: UnlikeFoodScreenState,
    columnSize : Int,
    onCardClicked: (String) -> Unit,
    onBackClicked: () -> Unit = {},
    onSkipClicked: () -> Unit = {},
    navigateToNextPage: () -> Unit,
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = AconTheme.color.Gray9)
    ){

        OnboardingTopBar(
            totalPages = screenState.totalPages,
            currentPage = screenState.currentPage,
            onLeadingIconClicked = {
                onBackClicked()
            },
            onTrailingIconClicked = {
                onSkipClicked()
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 20.dp, end = 20.dp, top = 50.dp, bottom = 50.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ){
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.TopStart
            ){
                Column(
                    modifier = Modifier
                ){
                    Text(
                        text = "01",
                        color = AconTheme.color.Gray5,
                        style = AconTheme.typography.head4_24_sb,
                        //textAlign = TextAlign.Center,
                    )
                    Text(
                        text = "어떤 음식을 피하고 싶으신가요?",
                        color = Color.White,
                        style = AconTheme.typography.head6_20_sb,
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ){
                FoodGrid(
                    modifier = modifier
                        .background(Color(0x00000000)),
                    columnSize = columnSize,
                    onCardClicked = { text ->
                        onCardClicked(text)
                    },
                    isNothingClicked = screenState.isNothingClicked,
                    selectedCard = screenState.selectedCard
                )
            }

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.BottomCenter
            ){

                //다음 버튼 누르면 currentPage 1씩 증가시키기 (아닌가? 페이지에 고정값으로 할당하는게 좋나)
                AconFilledLargeButton(
                    text = "다음",
                    textStyle = AconTheme.typography.head8_16_sb,
                    textColor = AconTheme.color.White,
                    enabledBackgroundColor = AconTheme.color.Gray5,
                    disabledBackgroundColor =  AconTheme.color.Gray8,
                    isEnabled = (screenState.selectedCard.size >= 1),
                    cornerRadius = 6.dp,
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
                    onClick = { navigateToNextPage() }
                )
            }
        }
    }
}

@Composable
@Preview
fun PreviewOnboardingScreen(){
    UnlikeFoodScreenContainer(
    )
}
