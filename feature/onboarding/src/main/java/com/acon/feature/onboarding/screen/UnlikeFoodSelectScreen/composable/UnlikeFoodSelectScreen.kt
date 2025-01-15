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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.acon.core.designsystem.component.button.AconFilledLargeButton
import com.acon.core.designsystem.component.dialog.AconTwoButtonDialog
import com.acon.core.designsystem.theme.AconTheme
import com.acon.feature.onboarding.component.FoodGrid
import com.acon.feature.onboarding.component.FoodItem
import com.acon.feature.onboarding.component.OnboardingTopBar
import com.acon.feature.onboarding.screen.UnlikeFoodSelectScreen.UnlikeFoodScreenSideEffect
import com.acon.feature.onboarding.screen.UnlikeFoodSelectScreen.UnlikeFoodScreenViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

/*
* TODO:
*  1) 탑바 뒤로가기 아이콘 & 다음 버튼 버그 (없음 누르고 취소하면 안없어짐)
*  3) 두번째 온보딩 화면으로 넘어가는 것까지 Route에서 연결해보기
*  4) 다음 버튼 누를 때마다 서버로 넘겨주나? API 명세서 보고 확인해보기
*  7) 아키텍처 따라서 깔끔하게 정리 완료하기 (ViewModel 등등)
*  8) 마지막에 Content(Container), Screen 구분하기
* */

@Composable
fun UnlikeFoodScreenContainer(
    modifier: Modifier = Modifier,
    viewModel: UnlikeFoodScreenViewModel = hiltViewModel()
){
    val state = viewModel.collectAsState().value

    UnlikeFoodScreen(
        modifier = modifier,
        columnSize = 3,
        foodItemList = state.foodItems,
        onCardClicked = viewModel::onCardClicked,
        isNothingClicked = state.isNothingClicked,
        onBackClicked = viewModel::onBackClicked,
        onSkipClicked = viewModel::onSkipClicked,
        selectedCard = state.selectedCard,
        selectedCardCount = state.selectedCard.size,
        totalPages = state.totalPages,
        currentPage = state.currentPage
    )

    val openCloseDialog = remember { mutableStateOf(false) }

    viewModel.collectSideEffect {
        when(it){
            UnlikeFoodScreenSideEffect.ShowCloseDialog -> {
                openCloseDialog.value = true
            }
            UnlikeFoodScreenSideEffect.NavigateToPreviousPage -> {
                //이전 페이지로 가는 sideEffect 구현하기
            }
        }
    }

    if (openCloseDialog.value) {
        AconTwoButtonDialog(
            title = "취향분석을 그만둘까요?",
            content = "선호도 조사만이 남아있어요!\n1분 내로 빠르게 끝내실 수 있어요.",
            leftButtonContent = "그만두기",
            rightButtonContent = "계속하기",
            contentImage = 0,
            onDismissRequest = {
                openCloseDialog.value = false
            },
            onClickLeft = {
                openCloseDialog.value = false
                //그만두기 -> 어디로 보내야 함?
            },
            onClickRight = {
                openCloseDialog.value = false
                //계속하기 -> 그냥 다이얼로그만 닫기
            },
            isImageEnabled = false
        )
    }
    
}

@Composable
fun UnlikeFoodScreen(
    modifier: Modifier = Modifier,
    columnSize : Int,
    foodItemList: List<FoodItem>,
    onCardClicked: (String) -> Unit,
    isNothingClicked: Boolean,
    onBackClicked: (Int) -> Unit,
    onSkipClicked: () -> Unit,
    selectedCard : Set<String>,
    selectedCardCount: Int,
    totalPages: Int,
    currentPage: Int,
    navigateToNextPage: (Int) -> Unit,
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = AconTheme.color.Gray9)
    ){

        OnboardingTopBar(
            totalPages = totalPages,
            currentPage = currentPage,
            onLeadingIconClicked = {
                onBackClicked(currentPage)
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
                modifier = Modifier.fillMaxWidth().weight(1f),
                contentAlignment = Alignment.Center
            ){
                FoodGrid(
                    modifier = modifier
                        .background(Color(0x00000000)),
                    columnSize = columnSize,
                    foodItemList = foodItemList,
                    onCardClicked = { text ->
                        onCardClicked(text)
                    },
                    isNothingClicked = isNothingClicked,
                    selectedCard = selectedCard
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
                    isEnabled = (selectedCardCount >= 1),
                    cornerRadius = 6.dp,
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
                    onClick = { navigateToNextPage(Int) }
                )
            }
        }
    }
}

@Composable
@Preview
fun previewOnboardingScreen(){
    UnlikeFoodScreenContainer()
}
