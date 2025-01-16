package com.acon.feature.upload

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.acon.core.designsystem.component.button.AconFilledLargeButton
import com.acon.core.designsystem.theme.AconTheme
import com.acon.feature.upload.component.DotoriIndicator

@Composable
fun UploadContainer(
    modifier: Modifier = Modifier,
    viewModel: UploadViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit,
    onNavigateToSuccess: () -> Unit
) {
    val state by viewModel.container.stateFlow.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.container.sideEffectFlow.collect { effect ->
            when (effect) {
                is UploadSideEffect.NavigateToSuccess -> {
                    onNavigateToSuccess()
                }
                is UploadSideEffect.NavigateBack -> {
                    onNavigateBack()
                }
            }
        }
    }

    UploadScreen(
        modifier = modifier,
        state = state,
        onIntent = viewModel::onIntent
    )
}

@Composable
fun UploadScreen(
    modifier: Modifier = Modifier,
    state: UploadState,
    onIntent: (UploadIntent) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = AconTheme.color.Gray9)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { onIntent(UploadIntent.NavigateBack) }) {
                val imageVector =
                    ImageVector.vectorResource(id = com.acon.core.designsystem.R.drawable.ic_dissmiss_24)
                Icon(
                    imageVector = imageVector,
                    contentDescription = "Close",
                    tint = AconTheme.color.White
                )
            }

            Text(
                text = "업로드",
                style = AconTheme.typography.title2_20_b,
                color = AconTheme.color.White
            )
            Spacer(modifier = Modifier.width(24.dp))
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Spacer(modifier = Modifier.padding(top = 32.dp))
            Text(
                text = "이제 도토리를\n떨어트려볼까요?",
                style = AconTheme.typography.head6_20_sb,
                color = AconTheme.color.White
            )

            Spacer(modifier = Modifier.padding(top = 8.dp))
            Text(
                text = "도토리를 사용해 리뷰를 남겨주세요.",
                style = AconTheme.typography.body3_13_reg,
                color = AconTheme.color.Gray3
            )

            Spacer(modifier = Modifier.padding(top = 8.dp))
            Text(
                text = "보유한 도토리 수 ${state.ownedDotoriCount}/25",
                style = AconTheme.typography.body4_12_reg,
                color = AconTheme.color.Main_org1
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(state.maxCount) { index ->
                    DotoriIndicator(
                        isSelected = index < state.selectedCount,
                        onClick = {
                            if (index < state.selectedCount) {
                                onIntent(UploadIntent.DeselectDotori)
                            } else {
                                onIntent(UploadIntent.SelectDotori)
                            }
                        }
                    )
                }
            }

            Text(
                text = "${state.selectedCount}/${state.maxCount}",
                style = AconTheme.typography.body4_12_reg,
                color = AconTheme.color.White
            )
        }

        AconFilledLargeButton(
            text = "도토리로 리뷰남기기",
            textStyle = AconTheme.typography.head8_16_sb,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            enabledBackgroundColor = AconTheme.color.Main_org1,
            disabledBackgroundColor = AconTheme.color.Gray7,
            isEnabled = state.isButtonEnabled,
            onClick = { onIntent(UploadIntent.UploadDotori) }
        )
    }
}
