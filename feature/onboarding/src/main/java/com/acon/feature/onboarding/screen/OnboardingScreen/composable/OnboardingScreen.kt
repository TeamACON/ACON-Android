package com.acon.feature.onboarding.screen.OnboardingScreen.composable

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
import com.acon.feature.onboarding.component.FoodGrid
import com.acon.feature.onboarding.component.FreqPlaceSelectGrid
import com.acon.feature.onboarding.component.OnboardingTopBar
import com.acon.feature.onboarding.component.PreferFoodTypeSelectGrid
import com.acon.feature.onboarding.component.PreferPlaceTypeSelectGrid
import com.acon.feature.onboarding.screen.OnboardingScreen.OnboardingPageState
import com.acon.feature.onboarding.screen.OnboardingScreen.OnboardingResult
import com.acon.feature.onboarding.screen.OnboardingScreen.OnboardingScreenSideEffect
import com.acon.feature.onboarding.screen.OnboardingScreen.OnboardingScreenState
import com.acon.feature.onboarding.screen.OnboardingScreen.OnboardingViewModel
import com.acon.feature.onboarding.screen.PrefResultLoadingScreen.PrefResultLoadingScreenSideEffect
import com.acon.feature.onboarding.screen.delete.UnlikeFoodSelectScreen.UnlikeFoodScreenSideEffect
import com.acon.feature.onboarding.screen.delete.UnlikeFoodSelectScreen.UnlikeFoodScreenState
import com.acon.feature.onboarding.screen.delete.UnlikeFoodSelectScreen.UnlikeFoodScreenViewModel
import com.acon.feature.onboarding.screen.delete.UnlikeFoodSelectScreen.composable.UnlikeFoodScreen
import com.acon.feature.onboarding.type.FoodItems
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun OnboardingContainer(
    modifier: Modifier = Modifier,
    viewModel: OnboardingViewModel = hiltViewModel(),
    navigateToLoadingView: () -> Unit = {},
    navigateToSpotListView: () -> Unit = {}
){
    val state = viewModel.collectAsState().value

    OnboardingScreen(
        modifier = modifier,
        screenState = state,
        onCardClicked = viewModel::onCardClicked,
        onBackClicked = viewModel::onBackClicked,
        onSkipClicked = viewModel::showDialog,
        navigateToNextPage = viewModel::navigateToNextPage,
    )

    viewModel.collectSideEffect {
        when(it){
            is OnboardingScreenSideEffect.NavigateToLoadingPage -> {
                navigateToLoadingView()
            }
            OnboardingScreenSideEffect.NavigateToSpotListView -> {
                navigateToSpotListView()
            }
        }
    }

    if (state.showDialog) {
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
                viewModel.skipConfirmed()
            },
            onClickRight = { // 계속하기
                viewModel.hideDialog()
            },
            isImageEnabled = false
        )
    }
}

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    screenState: OnboardingScreenState,
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

        //이 부분이 화면마다 달라져야 함.
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 15.dp, vertical = 30.dp),
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
                        text = when (val currentState = screenState.currentState) {
                            is OnboardingPageState.Page1State -> currentState.titleNum.toString()
                            is OnboardingPageState.Page2State -> currentState.titleNum.toString()
                            is OnboardingPageState.Page3State -> currentState.titleNum.toString()
                            is OnboardingPageState.Page4State -> currentState.titleNum.toString()
                            is OnboardingPageState.Page5State -> currentState.titleNum.toString()
                        },
                        color = AconTheme.color.Gray5,
                        style = AconTheme.typography.head4_24_sb,
                        modifier = modifier.padding(vertical = 7.dp)
                    )
                    Text(
                        text = stringResource(
                            id = when (val currentState = screenState.currentState) {
                                is OnboardingPageState.Page1State -> currentState.pageDescription
                                is OnboardingPageState.Page2State -> currentState.pageDescription
                                is OnboardingPageState.Page3State -> currentState.pageDescription
                                is OnboardingPageState.Page4State -> currentState.pageDescription
                                is OnboardingPageState.Page5State -> currentState.pageDescription
                            }
                        ),
                        color = Color.White,
                        style = AconTheme.typography.head4_24_sb,
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 20.dp),
                    ){
                        when (val currentState = screenState.currentState) {
                            is OnboardingPageState.Page1State -> {
                                FoodGrid(
                                    modifier = modifier.background(AconTheme.color.Gray9),
                                    columnSize = currentState.columnSize,
                                    foodItems = currentState.foodItems.toTypedArray(),
                                    onCardClicked = onCardClicked,
                                    isNothingClicked = currentState.isNothingClicked,
                                    selectedCard = currentState.selectedCard
                                )
                            }

                            is OnboardingPageState.Page2State -> {
                                PreferFoodTypeSelectGrid(
                                    modifier = modifier.background(AconTheme.color.Gray9),
                                    columnSize = currentState.columnSize,
                                    foodItems = currentState.foodItems.toTypedArray(),
                                    onCardClicked = onCardClicked,
                                    isAllClicked = currentState.selectedCard.size >= 3,
                                    selectedCard = currentState.selectedCard
                                )
                            }

                            is OnboardingPageState.Page3State -> {
                                FreqPlaceSelectGrid(
                                    modifier = modifier.background(AconTheme.color.Gray9),
                                    columnSize = currentState.columnSize,
                                    placeItems = currentState.placeItems.toTypedArray(),
                                    onCardClicked = onCardClicked,
                                    selectedCard = currentState.selectedCard
                                )
                            }

                            is OnboardingPageState.Page4State -> {
                                FreqPlaceSelectGrid(
                                    modifier = modifier.background(AconTheme.color.Gray9),
                                    columnSize = currentState.columnSize,
                                    placeItems = currentState.placeItems.toTypedArray(),
                                    onCardClicked = onCardClicked,
                                    selectedCard = currentState.selectedCard
                                )
                            }

                            is OnboardingPageState.Page5State -> {
                                PreferPlaceTypeSelectGrid(
                                    modifier = modifier.background(AconTheme.color.Gray9),
                                    columnSize = currentState.columnSize,
                                    placeItems = currentState.placeItems.toTypedArray(),
                                    onCardClicked = onCardClicked,
                                    selectedCard = currentState.selectedCard
                                )
                            }
                        }
                    }
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
                    isEnabled = when (val currentState = screenState.currentState) {
                        is OnboardingPageState.Page1State -> currentState.selectedCard.isNotEmpty()
                        is OnboardingPageState.Page2State -> currentState.selectedCard.size == 3
                        is OnboardingPageState.Page3State -> currentState.selectedCard.isNotEmpty()
                        is OnboardingPageState.Page4State -> currentState.selectedCard.isNotEmpty()
                        is OnboardingPageState.Page5State -> currentState.selectedCard.size == 4
                    },
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
private fun PreviewOnboardingScreen(){
    OnboardingContainer(
    )
}
