package com.acon.feature.onboarding.screen.PreferredFoodRateScreen.composable

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
import com.acon.core.designsystem.component.button.AconFilledLargeButton
import com.acon.core.designsystem.theme.AconTheme
import com.acon.feature.onboarding.component.OnboardingTopBar
import com.acon.feature.onboarding.component.PreferFoodTypeSelectGrid
import com.acon.feature.onboarding.screen.PreferredFoodRateScreen.RatePreferFoodScreenState
import com.acon.feature.onboarding.type.FoodTypeItems

@Composable
fun PreferredFoodRateScreen(
    modifier: Modifier = Modifier,
    screenState: RatePreferFoodScreenState,
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
                    text = "02",
                    color = AconTheme.color.Gray5,
                    style = AconTheme.typography.head4_24_sb,
                    modifier = modifier.padding(vertical = 7.dp)
                )
                Text(
                    text = "선호 음식 Top3까지 순위를 매겨주세요",
                    color = Color.White,
                    style = AconTheme.typography.head4_24_sb,
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ){
                PreferFoodTypeSelectGrid(
                    modifier = modifier
                        .background(AconTheme.color.Gray9),
                    columnSize = columnSize,
                    foodItems = FoodTypeItems.entries.toTypedArray(),
                    onCardClicked = { text ->
                        onCardClicked(text)
                    },
                    selectedCard = screenState.selectedCard,
                    isAllClicked = screenState.selectedCard.size == 3
                )
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
                    isEnabled = ( screenState.selectedCard.size == 3 ),
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
private fun PreviewOnboardingScreen2(){
    PreferredFoodRateScreenContainer()
}