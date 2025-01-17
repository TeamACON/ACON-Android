package com.acon.feature.onboarding.screen.PreferredFoodRateScreen.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.acon.core.designsystem.component.dialog.AconTwoButtonDialog
import com.acon.feature.onboarding.screen.PreferredFoodRateScreen.PreferredFoodRateScreenViewModel
import com.acon.feature.onboarding.screen.PreferredFoodRateScreen.RatePreferFoodScreenSideEffect
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun PreferredFoodRateScreenContainer(
    modifier: Modifier = Modifier,
    viewModel: PreferredFoodRateScreenViewModel = hiltViewModel(),
    navigateToPreviousPage: () -> Unit = {},
    navigateToNextPage: () -> Unit = {},
    navigateToLastLoadingPage: () -> Unit = {}
){
    val state = viewModel.collectAsState().value

    PreferredFoodRateScreen(
        modifier = modifier,
        screenState = state,
        columnSize = 3,
        onCardClicked = viewModel::onCardClicked,
        onSkipClicked = viewModel::showDialog,
        navigateToPreviousPage = viewModel::navigateToPreviousPage,
        navigateToNextPage = viewModel::navigateToNextPage,
    )

    viewModel.collectSideEffect {
        when(it){
            RatePreferFoodScreenSideEffect.NavigateToNextPage -> {
                navigateToNextPage()
            }
            RatePreferFoodScreenSideEffect.NavigateToPreviousPage -> {
                navigateToPreviousPage()
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