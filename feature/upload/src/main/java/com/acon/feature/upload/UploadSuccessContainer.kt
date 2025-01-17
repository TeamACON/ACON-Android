package com.acon.feature.upload

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.acon.core.designsystem.component.button.AconFilledLargeButton
import com.acon.core.designsystem.theme.AconTheme
import kotlinx.coroutines.delay

@Composable
fun UploadSuccessContainer(
    modifier: Modifier = Modifier,
    viewModel: UploadViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    val state by viewModel.container.stateFlow.collectAsStateWithLifecycle()

    UploadSuccessScreen(
        modifier = modifier,
        state = state,
        onNavigateBack = onNavigateBack
    )
}

@Composable
fun UploadSuccessScreen(
    modifier: Modifier = Modifier,
    state: UploadState,
    onNavigateBack: () -> Unit
) {
    LaunchedEffect(Unit) {
        delay(5000)
        onNavigateBack()
    }

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
            IconButton(onClick = onNavigateBack) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = com.acon.core.designsystem.R.drawable.ic_dissmiss_24),
                    contentDescription = "Close",
                    tint = AconTheme.color.White
                )
            }

            Text(
                text = "업로드",
                style = AconTheme.typography.title2_20_b,
                color = AconTheme.color.White
            )

            Spacer(modifier = Modifier.width(48.dp))
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {
            Text(
                text = "가게명에 대한",
                style = AconTheme.typography.head6_20_sb,
                color = AconTheme.color.White,
                textAlign = TextAlign.Start
            )
            Text(
                text = "리뷰 작성을 완료했어요!",
                style = AconTheme.typography.head6_20_sb,
                color = AconTheme.color.White,
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "당신의 취향이 가득 담긴 장소길 바라요",
                style = AconTheme.typography.body3_13_reg,
                color = AconTheme.color.Gray3
            )

            Spacer(modifier = Modifier.height(40.dp))
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.img_upload_finish),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(0.8f)
                    .aspectRatio(0.7f),
            )

            Spacer(modifier = Modifier.height(80.dp))
            Text(
                text = "5초 후 자동으로 닫힙니다.",
                style = AconTheme.typography.body3_13_reg,
                color = AconTheme.color.Gray3,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

            AconFilledLargeButton(
                text = "확인",
                textStyle = AconTheme.typography.head8_16_sb,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                disabledBackgroundColor = AconTheme.color.Gray7,
                enabledBackgroundColor = AconTheme.color.Gray7,
                onClick = onNavigateBack
            )
        }
    }
}
