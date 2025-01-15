package com.acon.feature.onboarding.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun OnboardingTopBar(
    modifier: Modifier = Modifier,
    selectedCardCount: Int,
    totalPages: Int,
    currentPage: Int,
    leadingIcon: @Composable () -> Unit = {
        Box {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowLeft,
                contentDescription = "Back",
                tint = Color.White,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .size(50.dp)
                    .padding(start = 15.dp),
            )
        }
    },
    content: @Composable () -> Unit = {},
    trailingIcon: @Composable () -> Unit = {
        SkipButton()
    },
){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF1A1B1E))
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF1A1B1E))
                .height(44.dp)
        )

        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(Color(0xFF1A1B1E))
                .height(56.dp),
        ){

            if ( selectedCardCount >= 1 ) {
                leadingIcon()
            }

            Box(
                modifier = modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 15.dp)
            ){
                trailingIcon()
            }
        }

        OnboardingProgressIndicator(
            modifier = modifier,
            totalPages = totalPages,
            currentPage = currentPage,
        )
    }
}


@Composable
@Preview
fun OnboardingTopBarPreivew(){
    OnboardingTopBar(
        selectedCardCount = 1,
        totalPages = 6,
        currentPage = 4
    )
}