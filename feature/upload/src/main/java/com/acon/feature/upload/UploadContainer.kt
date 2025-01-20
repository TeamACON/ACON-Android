package com.acon.feature.upload

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.acon.core.designsystem.blur.LocalHazeState
import com.acon.core.designsystem.component.button.AconFilledLargeButton
import com.acon.core.designsystem.noRippleClickable
import com.acon.core.designsystem.theme.AconTheme
import com.acon.feature.upload.component.DotoriIndicator
import com.acon.feature.upload.component.LocationSearchBottomSheet
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import dev.chrisbanes.haze.hazeSource

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

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = AconTheme.color.Gray9)
    ) {
        when (state.currentStep) {
            UploadStep.LOCATION_SELECTION -> {
                SpotListScreen(
                    modifier = Modifier.fillMaxSize(),
                    state = state,
                    onIntent = viewModel::onIntent
                )
            }

            UploadStep.DOTORI_REVIEW -> {
                UploadScreen(
                    modifier = Modifier.fillMaxSize(),
                    state = state,
                    onIntent = viewModel::onIntent
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpotListScreen(
    modifier: Modifier = Modifier,
    state: UploadState,
    onIntent: (UploadIntent) -> Unit
) {
    var showLocationSearch by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .hazeSource(LocalHazeState.current)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .hazeSource(LocalHazeState.current)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { onIntent(UploadIntent.NavigateBack) }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = com.acon.core.designsystem.R.drawable.ic_dissmiss_24),
                        contentDescription = "Close",
                        tint = AconTheme.color.White
                    )
                }

                Text(
                    text = "업로드",
                    style = AconTheme.typography.head5_22_sb,
                    color = AconTheme.color.White
                )

                Spacer(modifier = Modifier.width(48.dp))
            }

            Text(
                text = "장소 등록",
                style = AconTheme.typography.head7_18_sb,
                color = AconTheme.color.White,
                modifier = Modifier.padding(start = 20.dp, bottom = 8.dp)
            )

            Spacer(modifier = Modifier.padding(top = 8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .background(
                        color = AconTheme.color.Gray8,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .noRippleClickable { showLocationSearch = true }
                    .padding(vertical = 14.dp, horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(
                        id = R.drawable.and_ic_location_gray_28
                    ),
                    contentDescription = null,
                    tint = if (state.selectedLocation != null)
                        AconTheme.color.White
                    else
                        AconTheme.color.Gray5,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = state.selectedLocation?.name ?: "가게명 등록하기",
                    style = AconTheme.typography.subtitle1_16_med,
                    color = if (state.selectedLocation != null)
                        AconTheme.color.White
                    else
                        AconTheme.color.Gray5
                )
            }
        }

        if (!showLocationSearch) {
            AconFilledLargeButton(
                text = "이곳에 도토리 남기기",
                textStyle = AconTheme.typography.head7_18_sb,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                disabledBackgroundColor = AconTheme.color.Gray7,
                enabledBackgroundColor = AconTheme.color.Main_org1,
                isEnabled = state.selectedLocation != null,
                onClick = { onIntent(UploadIntent.OnNextStep) }
            )
        }

        if (showLocationSearch) {
            ModalBottomSheet(
                onDismissRequest = { showLocationSearch = false },
                sheetState = sheetState,
                containerColor = AconTheme.color.Gla_w_10,
                dragHandle = null,
            ) {
                LocationSearchBottomSheet(
                    onDismiss = { showLocationSearch = false },
                    onLocationSelected = { locationItem ->
                        showLocationSearch = false
                        onIntent(UploadIntent.SelectLocation(locationItem))
                    }
                )
            }
        }
    }
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
            .statusBarsPadding()
            .navigationBarsPadding()
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
                text = "도토리를 떨어트려\n리뷰를 남겨 볼까요?",
                style = AconTheme.typography.head6_20_sb,
                color = AconTheme.color.White
            )

            Spacer(modifier = Modifier.padding(top = 8.dp))
            Text(
                text = "아래의 도토리 아이콘을 클릭해 리뷰를 남겨주세요.",
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

        Spacer(modifier = Modifier.padding(top = 52.dp))
        Text(
            text = "${state.selectedCount}/${state.maxCount}",
            style = AconTheme.typography.body4_12_reg,
            color = AconTheme.color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.padding(top = 8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(state.maxCount) { index ->
                DotoriIndicator(
                    index = index,
                    isSelected = index < state.selectedCount,
                    onClick = {
                        if (index < state.selectedCount) {
                            onIntent(UploadIntent.DeselectDotori(index))
                        } else {
                            onIntent(UploadIntent.SelectDotori(index))
                        }
                    }
                )
            }
        }

        Spacer(modifier = Modifier.padding(top = 12.dp))
        Text(
            text = "도토리를 터치해보세요.",
            style = AconTheme.typography.body2_14_reg,
            color = AconTheme.color.Gray3,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(top = 12.dp, bottom = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            if (state.animatingIndex != null) {
                LottieAnimation(
                    composition = rememberLottieComposition(
                        spec = LottieCompositionSpec.RawRes(
                            when (state.animatingIndex) {
                                0 -> R.raw.dotori1
                                1 -> R.raw.dotori2
                                2 -> R.raw.dotori3
                                3 -> R.raw.dotori4
                                else -> R.raw.dotori5
                            }
                        )
                    ).value,
                    modifier = Modifier
                        .fillMaxSize()
                        .aspectRatio(1f)
                        .scale(1f),
                    iterations = 1,
                    isPlaying = true
                )
            }
        }

        AconFilledLargeButton(
            text = "도토리로 리뷰남기기",
            textStyle = AconTheme.typography.head8_16_sb,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            enabledBackgroundColor = AconTheme.color.Main_org1,
            disabledBackgroundColor = AconTheme.color.Gray7,
            isEnabled = state.isButtonEnabled,
            onClick = { onIntent(UploadIntent.UploadDotori) }
        )
    }
}
