package com.acon.feature.onboarding.screen.PerferredPlaceRateScreen.composable

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.acon.core.designsystem.component.button.AconFilledLargeButton
import com.acon.core.designsystem.component.dialog.AconTwoButtonDialog
import com.acon.core.designsystem.theme.AconTheme
import com.acon.feature.onboarding.R
import com.acon.feature.onboarding.component.OnboardingTopBar
import com.acon.feature.onboarding.component.PreferFoodTypeSelectGrid
import com.acon.feature.onboarding.component.PreferPlaceTypeSelectGrid
import com.acon.feature.onboarding.screen.PerferredPlaceRateScreen.PreferredPlaceRateScreenSideEffect
import com.acon.feature.onboarding.screen.PerferredPlaceRateScreen.PreferredPlaceRateScreenState
import com.acon.feature.onboarding.screen.PerferredPlaceRateScreen.PreferredPlaceRateScreenViewModel
import com.acon.feature.onboarding.screen.PreferredFoodRateScreen.PreferredFoodRateScreenViewModel
import com.acon.feature.onboarding.screen.PreferredFoodRateScreen.RatePreferFoodScreenSideEffect
import com.acon.feature.onboarding.screen.PreferredFoodRateScreen.RatePreferFoodScreenState
import com.acon.feature.onboarding.type.FoodTypeItems
import com.acon.feature.onboarding.type.PreferPlaceItems
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun PreferredPlaceRateScreenContainer(
    modifier: Modifier = Modifier,
    viewModel: PreferredPlaceRateScreenViewModel = hiltViewModel(),
    navigateToPreviousPage: () -> Unit = {},
    navigateToNextPage: () -> Unit = {},
    navigateToLastLoadingPage: () -> Unit = {}
){
    val state = viewModel.collectAsState().value

    PreferredPlaceRateScreen(
        modifier = modifier,
        screenState = state,
        columnSize = 2,
        onCardClicked = viewModel::onCardClicked,
        onSkipClicked = viewModel::showDialog,
        navigateToPreviousPage = viewModel::navigateToPreviousPage,
        navigateToNextPage = viewModel::navigateToNextPage,
    )

    viewModel.collectSideEffect {
        when(it){
            PreferredPlaceRateScreenSideEffect.NavigateToNextPage -> {
                navigateToNextPage()
            }
            PreferredPlaceRateScreenSideEffect.NavigateToPreviousPage -> {
                navigateToPreviousPage()
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

@Composable
fun PreferredPlaceRateScreen(
    modifier: Modifier = Modifier,
    screenState: PreferredPlaceRateScreenState,
    columnSize: Int,
    onCardClicked: (String) -> Unit,
    onSkipClicked: () -> Unit,
    navigateToPreviousPage: () -> Unit,
    navigateToNextPage: () -> Unit
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
                navigateToPreviousPage()
            },
            onTrailingIconClicked = {
                onSkipClicked()
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 50.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ){
            Column(
                modifier = Modifier
            ){
                Text(
                    text = "05",
                    color = AconTheme.color.Gray5,
                    style = AconTheme.typography.head4_24_sb,
                    modifier = modifier.padding(vertical = 7.dp)
                )
                Text(
                    text = stringResource(R.string.onboarding_5_title),
                    color = Color.White,
                    style = AconTheme.typography.head4_24_sb,
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp)
                ){
                    PreferPlaceTypeSelectGrid(
                        modifier = modifier
                            .background(AconTheme.color.Gray9),
                        columnSize = columnSize,
                        foodItems = PreferPlaceItems.entries.toTypedArray(),
                        onCardClicked = { id ->
                            onCardClicked(id)
                        },
                        selectedCard = screenState.selectedCard,
                    )
                }
            }

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.BottomCenter
            ){

                AconFilledLargeButton(
                    text = "다음",
                    textStyle = AconTheme.typography.head7_18_sb,
                    enabledBackgroundColor = AconTheme.color.Gray5,
                    disabledBackgroundColor =  AconTheme.color.Gray8,
                    isEnabled = ( screenState.selectedCard.size == 4 ),
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
private fun PreviewOnboardingScreen5(){
    PreferredPlaceRateScreenContainer()
}