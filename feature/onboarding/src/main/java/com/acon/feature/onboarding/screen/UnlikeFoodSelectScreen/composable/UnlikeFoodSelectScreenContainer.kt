package com.acon.feature.onboarding.screen.UnlikeFoodSelectScreen.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.acon.core.designsystem.component.dialog.AconTwoButtonDialog
import com.acon.feature.onboarding.R
import com.acon.feature.onboarding.screen.UnlikeFoodSelectScreen.UnlikeFoodScreenSideEffect
import com.acon.feature.onboarding.screen.UnlikeFoodSelectScreen.UnlikeFoodScreenViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

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
            title = stringResource(R.string.onboarding_alert_title),
            content = stringResource(R.string.onboarding_alert_description),
            leftButtonContent = stringResource(R.string.onboarding_alert_left_btn),
            rightButtonContent = stringResource(R.string.onboarding_alert_right_btn),
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